package az.banking.bankmanagementsystem.Security.Configuration;
import az.banking.bankmanagementsystem.Security.Filter.JwtTokenVerifierFilter;
import az.banking.bankmanagementsystem.Security.Filter.JwtUsernameAndPasswordAuthenticationFilter;
import az.banking.bankmanagementsystem.Security.Service.AuthenticaionUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static az.banking.bankmanagementsystem.Security.model.Enum.UserPermission.*;
import static az.banking.bankmanagementsystem.Security.model.Enum.UserRole.ADMIN;
import static az.banking.bankmanagementsystem.Security.model.Enum.UserRole.USER;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
     private  final AuthenticaionUserService authenticaionUserService;
    private  final PasswordConfigartion passwordConfigartion;

    private static final String[] PUBLIC_URLS = {
            "/",
            "/login",
            "/api/v1/customers/creatCustomer",
            "/api/auth/**",    // Login, Register ??/
             "/api/public/**",//????
            "/telegram/data",
            // Swagger UI
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/configuration/**",
            "/webjars/**"
    };
  @Bean
  public   SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationConfiguration authConfig) throws Exception {


      AuthenticationManager authenticationManager = authConfig.getAuthenticationManager();

      http.authorizeHttpRequests(
              auth -> auth


                              // CUSTOMERS
                             .requestMatchers(HttpMethod.PUT, "/api/v1/customers/**").hasRole(ADMIN.name())
                             .requestMatchers(HttpMethod.DELETE, "/api/v1/customers/**").hasAuthority(CUSTOMER_DELETE.getPermission())
                             // ACCOUNTS
                             .requestMatchers(HttpMethod.POST, "/api/accounts/").hasAnyRole(ADMIN.name(), USER.name())
                              .requestMatchers(HttpMethod.GET, "/api/accounts/**").hasAuthority(ACCOUNT_READ.getPermission())
                             // TRANSACTIONS
                             .requestMatchers("/bank/transactions/deposit").hasAuthority(TRANSACTION_DEPOSIT.getPermission())
                             .requestMatchers("/bank/transactions/withdraw").hasAuthority(TRANSACTION_WITHDRAW.getPermission())
                              .requestMatchers(PUBLIC_URLS).permitAll()
                             .anyRequest().authenticated())
              .authenticationManager(authenticationManager)
              .authenticationProvider(daoAuthenticationProvider())

              .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager))
              .addFilterBefore(new JwtTokenVerifierFilter(),JwtUsernameAndPasswordAuthenticationFilter.class)
              .httpBasic(Customizer.withDefaults()) //???? burada niye var? olmadan ne olar? nece edim ki , authentication ola bilim..! default olaraq niye gelmir?
              .csrf(AbstractHttpConfigurer::disable);
      return http.build();


  }



    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        var provider = new DaoAuthenticationProvider(authenticaionUserService);
        provider.setPasswordEncoder(passwordConfigartion.passwordEncoder());
        return provider;
    }
}
