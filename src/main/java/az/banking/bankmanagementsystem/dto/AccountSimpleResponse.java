package az.banking.bankmanagementsystem.dto;
import az.banking.bankmanagementsystem.enums.AccountStatus;
import az.banking.bankmanagementsystem.enums.AccountType;
import lombok.Builder;
import lombok.Data;
import az.banking.bankmanagementsystem.enums.Currency;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@Builder
@NoArgsConstructor

public class AccountSimpleResponse {
   // private String accountNumber;-->Business logic-e nezeren lazim ola biler
    private String maskedAccountNumber; // "AZ001******7890"
    private BigDecimal balance;
    private AccountType accountType;
    private Currency currency;
    private AccountStatus status;


    public AccountSimpleResponse(String accountNumber, BigDecimal balance,
                                 AccountType accountType, Currency currency,
                                 AccountStatus status) {

        this.balance = balance;
        this.accountType = accountType;
        this.currency = currency;
        this.status = status;
        this.maskedAccountNumber = maskAccountNumber(accountNumber);
    }

    private String maskAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.length() < 8) return accountNumber;
        String firstFour = accountNumber.substring(0, 4);
        String lastFour = accountNumber.substring(accountNumber.length() - 4);
        return firstFour + "*******" + lastFour;
    }

}
