package az.banking.bankmanagementsystem.error.exception;

import az.banking.bankmanagementsystem.error.model.ApiException;

public class DuplicateAccountNumberException extends ApiException {
    public DuplicateAccountNumberException(String message) {
        super(message,404);
    }
}
