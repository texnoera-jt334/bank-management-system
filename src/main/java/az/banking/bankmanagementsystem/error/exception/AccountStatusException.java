package az.banking.bankmanagementsystem.error.exception;

import az.banking.bankmanagementsystem.error.model.ApiException;

public class AccountStatusException extends ApiException {
    public AccountStatusException(String message) {
        super(message, 404);
    }
}
