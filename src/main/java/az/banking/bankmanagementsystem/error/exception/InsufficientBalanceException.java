package az.banking.bankmanagementsystem.error.exception;

import az.banking.bankmanagementsystem.error.model.ApiException;

public class InsufficientBalanceException extends ApiException {
    public InsufficientBalanceException(String message) {
        super(message,400);
    }
}
