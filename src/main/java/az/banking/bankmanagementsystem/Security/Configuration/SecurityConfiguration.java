package az.banking.bankmanagementsystem.Security.Configuration;
import az.banking.bankmanagementsystem.Security.Service.SecurityUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static az.banking.bankmanagementsystem.Security.model.Enum.UserPermission.*;




@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
     private  final SecurityUserService securityUserService;
    private  final PasswordConfigartion passwordConfigartion;


  @Bean
  public   SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationConfiguration authenticationConfiguration) throws Exception {

      http.authorizeHttpRequests(
              auth ->
                     auth.requestMatchers("/transactions/**").hasAnyAuthority(USERS_BALANCES_READ_TRANSACTION.getPermission())
                             .requestMatchers( "/Employee/**").hasAnyAuthority(USERS_WRITE_DELETE.getPermission())
                             .requestMatchers("/loan/**").hasAnyAuthority(USERS_CREDIT_PAYMENT.getPermission())
                             .requestMatchers( "/api/accounts/***").permitAll()//--->????
                             .anyRequest().authenticated())
              .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .httpBasic(Customizer.withDefaults())
              .csrf(AbstractHttpConfigurer::disable);
      return http.build();


  }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        var provider = new DaoAuthenticationProvider(securityUserService);
        provider.setPasswordEncoder(passwordConfigartion.passwordEncoder());
        return provider;
    }
}
