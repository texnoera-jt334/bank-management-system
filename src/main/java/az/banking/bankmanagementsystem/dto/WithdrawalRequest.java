package az.banking.bankmanagementsystem.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawalRequest {
    @NotNull(message = "Hesab nomrsi menfi ola bilmez")
    private String accountNumber;
    @NotNull(message = "Məbləğ mütləqdir")
    @Positive(message = "Məbləğ müsbət olmalıdır")
    private BigDecimal amount;         // Məbləğ

    private String description;        // "ATM çıxarışı", "Kassa" vb.

    @NotNull(message = "PIN kodu mütləqdir")
    private String pinCode;            // Təhlükəsizlik kodu
}
