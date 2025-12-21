package az.banking.bankmanagementsystem.validators;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Objects;
public class RequesCustomertFinValidator implements ConstraintValidator<CustomerFin, String> {

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            if (Objects.isNull(value)) {
                return false;
            }

            if (!Character.isUpperCase(value.charAt(0))) {
                return false;
            }

            if (value.length() < 7) {
                return false;
            }

            if (!value.matches(".*\\d.*")) {
                return false;
            }
            if (value.chars().allMatch(Character::isLetter)){
                return false;
            }

            if(value.chars().allMatch(Character::isDigit)){
                return false;
            }

            return true;
        }

    }


