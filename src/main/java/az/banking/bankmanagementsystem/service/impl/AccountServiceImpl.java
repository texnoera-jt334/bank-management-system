package az.banking.bankmanagementsystem.service.impl;

import az.banking.bankmanagementsystem.DTO.AccountRequest;
import az.banking.bankmanagementsystem.DTO.AccountResponse;
import az.banking.bankmanagementsystem.entity.Account;
import az.banking.bankmanagementsystem.entity.Customer;
import az.banking.bankmanagementsystem.enums.AccountStatus;
import az.banking.bankmanagementsystem.enums.Currency;
import az.banking.bankmanagementsystem.exception.AccountNotFoundException;
import az.banking.bankmanagementsystem.repository.AccountRepository;
import az.banking.bankmanagementsystem.service.AccountService;
import az.banking.bankmanagementsystem.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;


    // Müştəri üçün yeni hesab açmaq

    @Override
    public AccountResponse creatAccount(AccountRequest request) {

        log.info("Yeni hesab yaradılır: {}", request.getAccountNumber());

        // Müştərinin mövcud olub-olmadığını yoxla
        Customer customer = customerService.getCustomerByFinCode(request.getCumtomerFinCode());

        //  Hesab nömrəsinin unikal olub-olmadığını yoxla
        if (accountRepository.existsByAccountNumber(request.getAccountNumber())) {
            throw new RuntimeException("Hesab nömrəsi artıq mövcuddur: " + request.getAccountNumber());
        }

        // Yeni hesab yarat
        Account account = Account.builder()
                .accountNumber(request.getAccountNumber())
                .customer(customer)
                .balance(request.getInitialBalance() != null ? request.getInitialBalance() : BigDecimal.ZERO)
                .accountType(request.getAccountType())
                .currency(request.getCurrency() != null ? request.getCurrency() : Currency.AZN)
                .status(AccountStatus.ACTIVE)
                .build();

        Account savedAccount = accountRepository.save(account);
        log.info("Yeni hesab yaradıldı: {}", savedAccount.getAccountNumber());

        return CreatAccountResponseDTO(savedAccount);
    }

    //Musteriye Accountla bagli butun melumati vermemek ucun neticeni AccountResponse ile otur
    private AccountResponse CreatAccountResponseDTO(Account account) {

        AccountResponse accountResponseDTO = new AccountResponse();

        accountResponseDTO.setMessenger(" Account Hesab yaradildi");
        accountResponseDTO.setAccountNumber(account.getAccountNumber());
        accountResponseDTO.setBalance(account.getBalance());
        accountResponseDTO.setAccountType(account.getAccountType());
        accountResponseDTO.setCurrency(account.getCurrency());
        accountResponseDTO.setStatus(account.getStatus());
        accountResponseDTO.setCreatedAt(account.getCreatedAt());

        return accountResponseDTO;

    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber).
                orElseThrow(() -> new AccountNotFoundException("Hesab tapilmadi"));
    }


}
