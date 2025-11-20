package az.banking.bankmanagementsystem.DTO;

import az.banking.bankmanagementsystem.enums.AccountStatus;
import az.banking.bankmanagementsystem.enums.AccountType;
import az.banking.bankmanagementsystem.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountResponse {
        private String messenger;
        private String accountNumber;
        private BigDecimal balance;
        private AccountType accountType;
        private Currency currency;
        private AccountStatus status;
        private LocalDateTime createdAt;

}
