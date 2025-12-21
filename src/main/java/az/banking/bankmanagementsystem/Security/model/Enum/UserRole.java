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

    USER(Sets.newHashSet(USERS_BALANCES_READ_TRANSACTION)),
    EMPLOYER(Sets.newHashSet(USERS_WRITE_DELETE)),
    LOAN(Sets.newHashSet(USERS_CREDIT_PAYMENT));
    private final Set<UserPermission> userPermissionSet;


    public Set<GrantedAuthority> getGrantedAuthorities() {
        return getUserPermissionSet().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}



