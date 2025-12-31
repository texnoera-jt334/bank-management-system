package az.banking.bankmanagementsystem.dto;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreatRequest {

    @NotBlank(message = "FIN code boş ola bilməz")
    @Pattern(
            regexp = "^[A-Z0-x9]{7}$",
            message = "FIN code 7 simvol olmalıdır (A-Z, 0-9)"
    )
    private String finCode;

    @NotBlank(message = "Ad boş ola bilməz")
    @Size(min = 2, max = 50, message = "Ad 2–50 simvol arası olmalıdır")
    private String name;

    @NotBlank(message = "Soyad boş ola bilməz")
    @Size(min = 2, max = 50, message = "Soyad 2–50 simvol arası olmalıdır")
    private String surname;

    @NotBlank(message = "Email boş ola bilməz")
    @Email(message = "Email formatı yanlışdır")
    @Size(max = 100)
    private String email;

    @NotBlank(message = "Telefon nömrəsi boş ola bilməz")
    @Pattern(
            regexp = "^\\+?[0-9]{9,15}$",
            message = "Telefon nömrəsi düzgün formatda deyil"
    )
    private String phoneNumber;

    @Size(max = 500, message = "Ünvan maksimum 500 simvol ola bilər")
    private String address;

    @NotBlank(message = "Şifrə boş ola bilməz")
    @Size(min = 8, max = 64, message = "Şifrə minimum 8 simvol olmalıdır")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).*$",
            message = "Şifrə ən azı 1 böyük hərf, 1 kiçik hərf və 1 rəqəm içerməlidir"
    )
    private String password;

    @NotNull(message = "Doğum tarixi boş ola bilməz")
    @Past(message = "Doğum tarixi keçmiş tarix olmalıdır")
    private LocalDate dateOfBirth;

}
