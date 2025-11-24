package az.banking.bankmanagementsystem.exception;

public class DuplicateAccountNumberException extends ApiException {
    public DuplicateAccountNumberException(String message) {
        super(message,404);
    }
}
