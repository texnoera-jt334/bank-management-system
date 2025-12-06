package az.banking.bankmanagementsystem.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class WithdrawalResponse {

    private String message;            // "Withdrawal uğurla tamamlandı"
    private Long transactionId;        // Tranzaksiya ID
    private String referenceNumber;    // Referans nömrəsi
    private BigDecimal newBalance;     // Yeni balans
    private LocalDateTime timestamp;   // Əməliyyat vaxtı
}
