package az.banking.bankmanagementsystem.validators;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequesCustomertFinValidator.class)
public @interface CustomerFin {

    String message() default "Invalid Fin ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
