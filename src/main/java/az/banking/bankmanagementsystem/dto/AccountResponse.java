package az.banking.bankmanagementsystem.dto;

import az.banking.bankmanagementsystem.entity.Customer;
import az.banking.bankmanagementsystem.enums.AccountStatus;
import az.banking.bankmanagementsystem.enums.AccountType;
import az.banking.bankmanagementsystem.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class AccountResponse  {
        private String accountNumber;
        private Customer customer;
        private BigDecimal balance;
        private AccountType accountType;
        private Currency currency;
        private AccountStatus status;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;



}
