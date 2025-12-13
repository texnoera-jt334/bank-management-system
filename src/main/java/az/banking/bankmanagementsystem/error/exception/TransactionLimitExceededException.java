package az.banking.bankmanagementsystem.error.exception;

import az.banking.bankmanagementsystem.error.model.ApiException;

public class TransactionLimitExceededException extends ApiException {
    public TransactionLimitExceededException(String message) {
        super(message,400);
    }
}
