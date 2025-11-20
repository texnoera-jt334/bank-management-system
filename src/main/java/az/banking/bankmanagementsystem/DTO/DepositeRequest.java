package az.banking.bankmanagementsystem.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositeRequest {
    private String AccountNumber;
    private BigDecimal amount;
    private String description;// "ATM depozit", "Kassa" vb.

    public void validate() {
        validateAccountNumber();
    }

    private void validateAccountNumber() {
        if (AccountNumber == null || AccountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Hesab nömrəsi mütləqdir");
        }
        if (AccountNumber.length() != 16) {
            throw new IllegalArgumentException("Hesab nömrəsi 16 rəqəm olmalıdır");
        }
        if (!AccountNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Hesab nömrəsi yalnız rəqəmlərdən ibarət olmalıdır");
        }
    }



}
