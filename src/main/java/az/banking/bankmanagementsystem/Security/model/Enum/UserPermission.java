package az.banking.bankmanagementsystem.Security.model.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserPermission {

    // ACCOUNT
    ACCOUNT_CREATE("account:create"),
    ACCOUNT_READ("account:read"),//hesablarini gormek
    // CUSTOMER
    CUSTOMER_CREATE("customer:create"),
    CUSTOMER_UPDATE("customer:update"),
    CUSTOMER_DELETE("customer:delete"),

    // TRANSACTION
    TRANSACTION_DEPOSIT("transaction:deposit"),
    TRANSACTION_WITHDRAW("transaction:withdraw"),
    TRANSACTION_BALANCE("transaction:balance"),

    // TELEGRAM / AI / INTEGRATION
    TELEGRAM_DATA_PUSH("telegram:data"),

    // ADMIN / SYSTEM
    USER_MANAGE("user:manage");



    private final String permission;
}