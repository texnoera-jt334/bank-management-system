package az.banking.bankmanagementsystem.DTO.Transaction;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DepositeResponse {

    private String message;
    private long transactionid;
    private String referancesNumber;
    private BigDecimal newBalance;
    private LocalDateTime timestamp;

}
