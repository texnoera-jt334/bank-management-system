package az.banking.bankmanagementsystem.service.Transaction;

import az.banking.bankmanagementsystem.DTO.Transaction.DepositeResponse;
import az.banking.bankmanagementsystem.DTO.Transaction.DepositeRequest;
import az.banking.bankmanagementsystem.entity.Account;
import az.banking.bankmanagementsystem.entity.Transaction;
import az.banking.bankmanagementsystem.enums.TransactionType;
import az.banking.bankmanagementsystem.exception.TransactionResourceNotFoundException;
import az.banking.bankmanagementsystem.repository.AccountRepository;
import az.banking.bankmanagementsystem.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TransactionDepositeService implements TransActionService{
     private final AccountRepository accountRepository;
     private final TransactionRepository transActionRepository;

    @Override
    @Transactional
    public DepositeResponse deposit(DepositeRequest depositeRequestDTO) {

       //Requesti yoxla

        depositeRequestDTO.validate();


        //Hesab yoxla
        Account account=accountRepository.findByAccountNumber(depositeRequestDTO.getAccountNumber())
                .orElseThrow(()->new TransactionResourceNotFoundException(
                        "hesab tapilmadi "+depositeRequestDTO.getAccountNumber()));

       //Status yoxlamaq
        if (!"Active".equals(account.getStatus())) {
            throw new IllegalStateException("Hesab aktiv deyil: " + account.getStatus());
        }

        //meblegi yoxlamaq  0 veya - ola biler
        validateAmount(depositeRequestDTO.getAmount());

        // 4. Balansı artır
        BigDecimal oldBalance = account.getBalance();
        BigDecimal newBalance = oldBalance.add(depositeRequestDTO.getAmount());
        account.setBalance(newBalance);
        accountRepository.save(account);

// 5. Tranzaksiya yarat -
        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(depositeRequestDTO.getAmount())
                .transactionType(TransactionType.DEPOSIT)
                .description(depositeRequestDTO.getDescription() != null ?
                        depositeRequestDTO.getDescription() : "Deposit əməliyyatı")
                .relatedAccount(null) // Deposit üçün relatedAccount null olur
                .referenceNumber(generateReferenceNumber())
                .createdAt(LocalDateTime.now())
                .build();


        Transaction savedTransaction = transActionRepository.save(transaction);

        return createSuccessResponse(savedTransaction, newBalance);
    }



    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Məbləğ müsbət olmalıdır");
        }

        // Maksimum depozit limiti
        BigDecimal maxDeposit = new BigDecimal("50000");
        if (amount.compareTo(maxDeposit) > 0) {
            throw new IllegalArgumentException("Maksimum depozit limiti: " + maxDeposit);
        }
    }


    private String generateReferenceNumber() {
        return "DEP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase() +
                "-" + System.currentTimeMillis();
    }

    private DepositeResponse createSuccessResponse(Transaction transaction, BigDecimal newBalance) {
        DepositeResponse response = new DepositeResponse();
        response.setMessage("Depozit ugurla tamamlandi");
        response.setTransactionid(transaction.getId()); // Long ID
        response.setReferancesNumber(transaction.getReferenceNumber());
        response.setNewBalance(newBalance);
        response.setTimestamp(LocalDateTime.now());
        return response;
    }

}
