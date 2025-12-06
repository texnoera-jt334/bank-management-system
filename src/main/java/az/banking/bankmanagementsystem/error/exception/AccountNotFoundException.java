package az.banking.bankmanagementsystem.error.exception;

import az.banking.bankmanagementsystem.error.model.ApiException;

public class AccountNotFoundException extends ApiException {
    public AccountNotFoundException(String message) {
        super(message, 404);
    }
}
