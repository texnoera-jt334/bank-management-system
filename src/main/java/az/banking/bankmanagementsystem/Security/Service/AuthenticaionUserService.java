package az.banking.bankmanagementsystem.Security.Service;

import az.banking.bankmanagementsystem.Security.repository.AuthUserEntity;
import az.banking.bankmanagementsystem.Security.repository.AuthUserRpository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@Configuration
@RequiredArgsConstructor
public class AuthenticaionUserService implements UserDetailsService {

    private final AuthUserRpository authUserRpository;

    @Override
    public UserDetails loadUserByUsername(String Fincode) throws UsernameNotFoundException {
        AuthUserEntity authUserEntity = authUserRpository.findByCustomer_FinCode(Fincode)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found with this Fincode",Fincode)));


        return User.builder()
                .username(authUserEntity.getCustomer().getFinCode())
                .password(authUserEntity.getPassword())
                .authorities(authUserEntity.getRole().getGrantedAuthorities().stream().toList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}

