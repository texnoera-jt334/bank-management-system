package az.banking.bankmanagementsystem.error.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class FieldValidationError {

    private String field;
    private String error;
}
