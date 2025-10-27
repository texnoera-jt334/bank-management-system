package az.banking.bankmanagementsystem.exception;

public class CustomerAlreadyExistsException extends ApiException {
    public CustomerAlreadyExistsException(String message) {
        super(message, 409);
    }
}
