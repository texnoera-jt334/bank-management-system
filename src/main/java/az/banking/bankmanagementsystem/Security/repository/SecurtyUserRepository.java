package az.banking.bankmanagementsystem.Security.repository;
import az.banking.bankmanagementsystem.Security.model.SecurtyUser;
import az.banking.bankmanagementsystem.Security.model.Enum.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
@RequiredArgsConstructor
public class SecurtyUserRepository {

        private final PasswordEncoder passwordEncoder;

        public Optional<SecurtyUser> findByUsername(String username) {
            SecurtyUser user = new SecurtyUser(
                    UserRole.USER.getGrantedAuthorities().stream().toList(),
                    "jane",
                    passwordEncoder.encode("12345"),
                    true,
                    true,
                    true,
                    true);

            SecurtyUser employer = new SecurtyUser(
                    UserRole.EMPLOYER.getGrantedAuthorities().stream().toList(),
                    "jack",
                    passwordEncoder.encode("1234"),
                    true,
                    true,
                    true,
                    true);

            SecurtyUser loan = new SecurtyUser(
                    UserRole.LOAN.getGrantedAuthorities().stream().toList(),
                    "jack",
                    passwordEncoder.encode("1234"),
                    true,
                    true,
                    true,
                    true);


            return Stream.of(user, loan,loan)
                    .filter(u -> u.getUsername().equals(username))
                    .findFirst();
        }


}
