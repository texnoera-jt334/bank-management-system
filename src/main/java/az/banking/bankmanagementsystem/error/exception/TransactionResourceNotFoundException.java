package az.banking.bankmanagementsystem.error.exception;

import az.banking.bankmanagementsystem.error.model.ApiException;

public class TransactionResourceNotFoundException extends ApiException {
    public TransactionResourceNotFoundException(String message) {
        super(message, 404);
    }
}
