package az.banking.bankmanagementsystem.service;

import az.banking.bankmanagementsystem.DTO.AccountRequest;
import az.banking.bankmanagementsystem.DTO.AccountResponse;
import az.banking.bankmanagementsystem.entity.Account;

public interface AccountService {

    AccountResponse creatAccount(AccountRequest request);

    Account getAccountByNumber(String accountNumber);
}
