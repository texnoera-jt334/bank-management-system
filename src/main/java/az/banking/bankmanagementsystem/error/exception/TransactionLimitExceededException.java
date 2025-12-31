package az.banking.bankmanagementsystem.error.exception;

import az.banking.bankmanagementsystem.error.model.ApiException;
import org.springframework.context.MessageSource;

public class TransactionLimitExceededException extends RuntimeException {

    public TransactionLimitExceededException(String message) {
        super(message);

    }
}
