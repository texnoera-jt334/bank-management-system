package az.banking.bankmanagementsystem.error.exception;

import az.banking.bankmanagementsystem.error.model.ApiException;

public class CustomerAlreadyExistsException extends ApiException {
    public CustomerAlreadyExistsException(String message) {
        super(message, 409);
    }
}
