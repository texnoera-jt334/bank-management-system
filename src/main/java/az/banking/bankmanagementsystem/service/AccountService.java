package az.banking.bankmanagementsystem.service;

import az.banking.bankmanagementsystem.dto.AccountRequest;
import az.banking.bankmanagementsystem.dto.AccountResponse;
import az.banking.bankmanagementsystem.dto.AccountSimpleResponse;


import java.util.List;

public interface AccountService {

    AccountResponse creatAccount(AccountRequest request);

    AccountSimpleResponse getAccountByNumber(String accountNumber);

    List<AccountSimpleResponse> getCustomerAccounts(String customerFinCode);
    /*
    Account getAccountById(Long id);
    List<Account> getCustomerAccounts(String customerFinCode);
    BigDecimal getAccountBalance(String accountNumber);
    Account updateAccountStatus(String accountNumber, AccountStatus status);
    boolean accountExists(String accountNumber);*/

}
