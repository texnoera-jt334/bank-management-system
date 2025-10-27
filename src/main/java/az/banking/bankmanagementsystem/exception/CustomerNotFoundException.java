package az.banking.bankmanagementsystem.exception;

public class CustomerNotFoundException extends ApiException {
    public CustomerNotFoundException(String message) {
        super(message, 404);
    }
}
