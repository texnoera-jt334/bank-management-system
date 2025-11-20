package az.banking.bankmanagementsystem.exception;

public class AccountNotFoundException extends ApiException {
    public AccountNotFoundException(String message) {
        super(message, 404);
    }
}
