package az.banking.bankmanagementsystem.service.impl;

import az.banking.bankmanagementsystem.dto.AccountRequest;
import az.banking.bankmanagementsystem.dto.AccountResponse;
import az.banking.bankmanagementsystem.dto.AccountSimpleResponse;
import az.banking.bankmanagementsystem.entity.Account;
import az.banking.bankmanagementsystem.entity.Customer;
import az.banking.bankmanagementsystem.enums.AccountStatus;
import az.banking.bankmanagementsystem.enums.Currency;
import az.banking.bankmanagementsystem.exception.AccountNotFoundException;
import az.banking.bankmanagementsystem.exception.DuplicateAccountNumberException;
import az.banking.bankmanagementsystem.repository.AccountRepository;
import az.banking.bankmanagementsystem.service.AccountService;
import az.banking.bankmanagementsystem.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
            throw new DuplicateAccountNumberException("Hesab nömrəsi artıq mövcuddur: " + request.getAccountNumber());
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

        return CreatAccountResponse(savedAccount);
    }

    //Musteriye Accountla bagli butun melumati vermemek ucun neticeni AccountResponse ile otur
    private AccountResponse CreatAccountResponse(Account account) {

       return AccountResponse.builder()
               .accountNumber(account.getAccountNumber())
               .customer(account.getCustomer())
               .balance(account.getBalance())
               .accountType(account.getAccountType())
               .currency(account.getCurrency())
               .status(account.getStatus())
               .createdAt(account.getCreatedAt()).build();

    }//XXXX account-la bagli melumat vermek
        @Override
        public AccountSimpleResponse getAccountByNumber(String accountNumber) {
            return accountRepository.findByAccountNumberforUser(accountNumber).
                    orElseThrow(() -> new AccountNotFoundException("Hesab tapilmadi"));
        }

    // Fin coda  nezeren butun Account melumati vermek
    @Override
    public List<AccountSimpleResponse> getCustomerAccounts(String customerFinCode) {
            return accountRepository.findByCustomerFinCode(customerFinCode).
                    orElseThrow(()->new AccountNotFoundException("Hesab tapilmadi"));


    }





}
