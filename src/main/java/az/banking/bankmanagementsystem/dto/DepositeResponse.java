package az.banking.bankmanagementsystem.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class DepositeResponse {

    private String message;
    private long transactionid;
    private String referancesNumber;
    private BigDecimal newBalance;
    private LocalDateTime timestamp;

}
