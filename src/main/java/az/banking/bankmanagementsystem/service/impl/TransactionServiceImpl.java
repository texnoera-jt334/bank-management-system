package az.banking.bankmanagementsystem.service.impl;
import az.banking.bankmanagementsystem.dto.DepositeResponse;
import az.banking.bankmanagementsystem.dto.DepositeRequest;
import az.banking.bankmanagementsystem.dto.WithdrawalRequest;
import az.banking.bankmanagementsystem.dto.WithdrawalResponse;
import az.banking.bankmanagementsystem.entity.Account;
import az.banking.bankmanagementsystem.entity.Transaction;
import az.banking.bankmanagementsystem.error.exception.*;
import az.banking.bankmanagementsystem.mapper.TransactionMapper;
import az.banking.bankmanagementsystem.repository.AccountRepository;
import az.banking.bankmanagementsystem.repository.TransactionRepository;
import az.banking.bankmanagementsystem.service.TransactionService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public   class TransactionServiceImpl implements TransactionService {


     private final AccountRepository accountRepository;
     private final TransactionRepository transActionRepository;
     private final TransactionMapper transactionMapper;
    @Value("${bank.withdrawal.daily-limit}")
    private BigDecimal dailyLimit;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DepositeResponse deposit(DepositeRequest depositeRequest) {

        Account account = accountRepository.findByAccountNumber(
                depositeRequest.getAccountNumber())
                .orElseThrow(() -> new TransactionResourceNotFoundException(
                        "Hesab tapılmadı: " + depositeRequest.getAccountNumber()));

        // 2. Status yoxla
        if (account.getStatus().equals("Active")) {
            throw new AccountStatusException( "Hesab aktiv deyil: " + account.getStatus());
        }

        // 3. Məbləği yoxla
        validateAmount(depositeRequest.getAmount());

        // 4. Balansı artır
        BigDecimal newBalance = account.getBalance().add(depositeRequest.getAmount());
        account.setBalance(newBalance);
        accountRepository.save(account);

        // 5.  MAPSTRUCT ilə Transaction yarat
        Transaction transaction = transactionMapper.toTransactionEntity(depositeRequest);

        // 6. MapStruct'ın map edə bilmədiyi field'ları set et
        transaction.setAccount(account);
        transaction.setReferenceNumber(generateReferenceNumber());
        transaction.setCreatedAt(LocalDateTime.now());

        // 7. DB-yə yaz
        Transaction savedTransaction = transActionRepository.save(transaction);

        // 8.  MAPSTRUCT ilə Response yarat
        return transactionMapper.toDepositeResponse(savedTransaction, newBalance);
    }



    @Override
    @Transactional(isolation= Isolation.READ_COMMITTED)
    public WithdrawalResponse withdraw(WithdrawalRequest withdrawalRequest) {

        log.info("Withdrawal emeliyyati basladi : {} ",withdrawalRequest.getAccountNumber());

        Account account= accountRepository.findByAccountNumber(withdrawalRequest.
                getAccountNumber()).
                orElseThrow(()->new AccountNotFoundException(
                        "hesab tapilmadi "+withdrawalRequest.getAccountNumber()));

        if (account.getStatus().equals("Active")) {
            throw new AccountStatusException( "Hesab aktiv deyil: " + account.getStatus());
        }

        //mebleg yoxla
        validateAmount(withdrawalRequest.getAmount());
        //balans yoxla
        validateBalance(account, withdrawalRequest.getAmount());
         // gunluk limiti yoxlamaq
        validateDailyWithdrawalLimit(account.getAccountNumber(),
                withdrawalRequest.getAmount(),dailyLimit);

        BigDecimal oldBalance = account.getBalance();
        BigDecimal newBalance = oldBalance.subtract(withdrawalRequest.getAmount());
        account.setBalance(newBalance);
        accountRepository.save(account);

        // 8. MapStruct ilə Transaction yarat
        Transaction transaction = transactionMapper.
                toWithdrawalTransactionEntity(withdrawalRequest);

        transaction.setAccount(account);
        transaction.setReferenceNumber(generateReferenceNumbe("WITH"));

        Transaction transaction1=transActionRepository.save(transaction);

        log.info("Withdrawal uğurla tamamlandı. Hesab: {}, Məbləğ: {}, Yeni balans: {}",
                withdrawalRequest.getAccountNumber(), withdrawalRequest.getAmount(), newBalance);

        return transactionMapper.toWithdrawalResponse(transaction1, newBalance);

    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new AmountValidationException("Məbləğ müsbət olmalıdır");
        }

        BigDecimal maxDeposit = new BigDecimal("50000");
        if (amount.compareTo(maxDeposit) > 0) {
            throw new AmountValidationException("Maksimum depozit limiti: " + maxDeposit);
        }
    }

    private String generateReferenceNumber() {
        return "DEP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase() +
                "-" + System.currentTimeMillis();
    }

    private void validateDailyWithdrawalLimit(String accountNumber,
                                              BigDecimal amount,
                                              BigDecimal dailyLimit) {
        BigDecimal dalylimit=dailyLimit;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twentyFourHoursAgo = now.minusHours(24);

        BigDecimal dailTotal=transActionRepository.findWithdrawalTotalInPeriod(
                accountNumber,
                twentyFourHoursAgo,
                now);


        if (dailTotal.add(amount).compareTo(dailyLimit) > 0) {
            throw new TransactionLimitExceededException("Günlük çıxarış limiti aşıldı. Bugünkü çıxarış: " + dalylimit + ", Limit: " + dailyLimit);
        }
    }

    private void validateBalance(Account account, BigDecimal amount) {

        if(account.getBalance().compareTo(amount)<0){
            throw new InsufficientBalanceException("Balansinizda kifayetqeder vesait yoxdur");
        }
    }



    public String generateReferenceNumbe(String prefix) {
        // Tarixi hissə (14 simvol) - YYYYMMDDHHMMSS
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        // Random böyük rəqəm (minimum 400000, maksimum 99999999)
        long randomPart = 400_000L + (long)(Math.random() * (99_999_999L - 400_000L));

        // Birləşdiririk
        StringBuilder ref = new StringBuilder(prefix + timestamp + randomPart);

        // Əgər hələ 30 simvola çatmayıbsa, təsadüfi hərflərlə tamamlayırıq
        while (ref.length() < 30) {
            ref.append((char) ('A' + new Random().nextInt(26)));
        }

        // Maksimum 30 simvol
        return ref.substring(0, 30);
    }


}
