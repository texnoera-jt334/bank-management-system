package az.banking.bankmanagementsystem.dto;


import az.banking.bankmanagementsystem.enums.AccountType;
import az.banking.bankmanagementsystem.enums.Currency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class AccountRequest {

@NotBlank(message = "Musteri Fin code mutleqdir")
@Size(min = 7,max = 7, message = "Fin 7 reqemli olmalidir")
private  String cumtomerFinCode;
@NotNull(message = "Hesab növü mütləqdir")
private  AccountType AccountType;
@DecimalMin(value = "0.0", message = "Başlanğıc balans mənfi ola bilməz")
private BigDecimal initialBalance;

private Currency currency;


}
