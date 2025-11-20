package az.banking.bankmanagementsystem.DTO.Acount;


import az.banking.bankmanagementsystem.enums.AccountType;
import az.banking.bankmanagementsystem.enums.Currency;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {
@NotBlank(message = "Hesab nomresi daxil edin")
@Size(min = 20,max = 20,message = "Yalniz 20 reqemli olmaldir.")
private  String accountNumber;

@NotBlank(message = "Musteri Fin code mutleqdir")
@Size(min = 7,max = 7, message = "Fin 7 reqemli olmalidir")
private  String cumtomerFinCode;
@NotNull(message = "Hesab növü mütləqdir")
private  AccountType AccountType;
@DecimalMin(value = "0.0", message = "Başlanğıc balans mənfi ola bilməz")
private BigDecimal initialBalance;

private Currency currency;


}
