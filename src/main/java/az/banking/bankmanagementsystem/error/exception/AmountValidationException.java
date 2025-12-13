package az.banking.bankmanagementsystem.error.exception;

import az.banking.bankmanagementsystem.error.model.ApiException;
//balances mengi olanda atilan exception
public class AmountValidationException extends ApiException {
    public AmountValidationException(String message) {
        super(message,400);
    }
}
