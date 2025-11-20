package az.banking.bankmanagementsystem.exception;

public class TransactionResourceNotFoundException extends ApiException {
    public TransactionResourceNotFoundException(String message) {
        super(message, 404);
    }
}
