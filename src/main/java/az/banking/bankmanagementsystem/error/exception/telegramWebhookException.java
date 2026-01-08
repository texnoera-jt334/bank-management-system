package az.banking.bankmanagementsystem.error.exception;

import az.banking.bankmanagementsystem.error.model.ApiException;

public class telegramWebhookException extends ApiException {

    public telegramWebhookException(String message) {
        super(message, 404);
    }
}
