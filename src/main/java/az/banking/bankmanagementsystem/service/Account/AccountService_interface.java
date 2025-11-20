package az.banking.bankmanagementsystem.service.Account;

import az.banking.bankmanagementsystem.DTO.Acount.AccountRequest;
import az.banking.bankmanagementsystem.DTO.Acount.AccountResponse;
import az.banking.bankmanagementsystem.entity.Account;
import az.banking.bankmanagementsystem.enums.AccountStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@Service
public interface AccountService_interface {


        AccountResponse creatAccount(AccountRequest request);
        Account getAccountByNumber(String accountNumber);
        /*Account getAccountById(Long id);
        List<Account> getCustomerAccounts(String customerFinCode);
        BigDecimal getAccountBalance(String accountNumber);
        Account updateAccountStatus(String accountNumber, AccountStatus status);
        boolean accountExists(String accountNumber);*/



}
