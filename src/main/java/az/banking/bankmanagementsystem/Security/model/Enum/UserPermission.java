package az.banking.bankmanagementsystem.Security.model.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPermission {

    BALANCES_READ("balances:read"),
    TRANSACTION_READ("transaction:read"),

    USER_WRITE("user:write"),
    USER_DELETE("user:delete"),

    CREDIT_INFORMATION("credit:information"),
    PAYMENT("payment");


    private final String permission;
}