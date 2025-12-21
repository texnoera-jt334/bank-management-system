package az.banking.bankmanagementsystem.Security.model.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPermission {


    USERS_BALANCES_READ_TRANSACTION("balances:read and transaction"),
    USERS_WRITE_DELETE("user:delete,write"),
    USERS_CREDIT_PAYMENT("credit:Information,payment");


    private final String permission;
}