package az.banking.bankmanagementsystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositeRequest {
    @NotBlank(message = "AccountNumber mütləq daxil edilməlidir")
    @Size(min = 20, max = 20, message = "Account Number 20 rəqəmli olmalıdır")
    private String accountNumber;
    @NotNull(message = "Məbləğ mütləq daxil edilməlidir")
    @DecimalMin(value = "1.00", message = "Minimum 1 AZN olmalıdır")
    @DecimalMax(value = "50000.00", message = "Maksimum 50000 AZN olmalıdır")
    private BigDecimal amount;

    private String description; //"ATM depozit", "Kassa" vb.


}
