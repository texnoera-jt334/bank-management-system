package az.banking.bankmanagementsystem.Security.Service;

import az.banking.bankmanagementsystem.Security.repository.SecurtyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

   private final SecurtyUserRepository securtyUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return securtyUserRepository.findByUsername(username).
                orElseThrow(()->new UsernameNotFoundException(String.format("User not found ",username)));
    }
}
