package az.banking.bankmanagementsystem.Security.model.Enum;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Set;
import java.util.stream.Collectors;

import static az.banking.bankmanagementsystem.Security.model.Enum.UserPermission.*;


@Getter
@AllArgsConstructor
public enum UserRole {
    USER(Set.of(
            ACCOUNT_CREATE,
            ACCOUNT_READ,              // öz hesablarını görsün
            TRANSACTION_BALANCE,
            TRANSACTION_DEPOSIT,
            TRANSACTION_WITHDRAW
            )),

    ADMIN(Set.of(
            USER_MANAGE,
            CUSTOMER_CREATE,
            CUSTOMER_UPDATE,
            CUSTOMER_DELETE,
            ACCOUNT_CREATE,
            ACCOUNT_READ,
            TELEGRAM_DATA_PUSH
    )),


    MANAGER(Set.of(
            ACCOUNT_READ,
            CUSTOMER_CREATE,
            TRANSACTION_DEPOSIT,
            TRANSACTION_WITHDRAW
    ));
    private final Set<UserPermission> userPermissionSet;


    public Set<GrantedAuthority> getGrantedAuthorities() {
        return getUserPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}



