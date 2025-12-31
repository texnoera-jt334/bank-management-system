package az.banking.bankmanagementsystem.dto;


import az.banking.bankmanagementsystem.enums.AccountType;
import az.banking.bankmanagementsystem.enums.Currency;
import az.banking.bankmanagementsystem.validators.CustomerFin;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountRequest {

@CustomerFin
private  String cumtomerFinCode;
@NotNull(message = "Hesab növü mütləqdir")
private  AccountType AccountType;
@DecimalMin(value = "0.0", message = "Başlanğıc balans mənfi ola bilməz")
private BigDecimal initialBalance;

private Currency currency;


}
