package az.banking.bankmanagementsystem.error.exception;


//extends RumtimeEception etmeyime sebeb men error Telegram APIEror CLASIna mapleyirem..
// cunki kod struktu orada ferqldiir,

public class TelegramChat_idNotFoundException extends RuntimeException {
    public TelegramChat_idNotFoundException(String message) {
        super(message);
    }
}
