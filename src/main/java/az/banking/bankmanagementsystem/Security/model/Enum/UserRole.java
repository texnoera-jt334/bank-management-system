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
            UserPermission.BALANCES_READ,
            UserPermission.TRANSACTION_READ
    )),

    EMPLOYER(Set.of(
            UserPermission.BALANCES_READ,
            UserPermission.USER_WRITE,
            UserPermission.USER_DELETE
    )),

    LOAN(Set.of(
            UserPermission.BALANCES_READ,
            UserPermission.CREDIT_INFORMATION,
            UserPermission.PAYMENT
    ));

    private final Set<UserPermission> userPermissionSet;


    public Set<GrantedAuthority> getGrantedAuthorities() {
        return getUserPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}



