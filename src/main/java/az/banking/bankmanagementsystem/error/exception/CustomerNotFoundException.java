package az.banking.bankmanagementsystem.error.exception;

import az.banking.bankmanagementsystem.error.model.ApiException;

public class CustomerNotFoundException extends ApiException {
    public CustomerNotFoundException(String message) {
        super(message, 404);
    }
}
