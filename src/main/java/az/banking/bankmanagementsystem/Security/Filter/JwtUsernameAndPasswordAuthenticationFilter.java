package az.banking.bankmanagementsystem.Security.Filter;

import az.banking.bankmanagementsystem.Security.model.UsernameAndPasswordAuthentication;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import java.io.IOException;
import java.util.Date;


public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

   private final AuthenticationManager authenticationManager;

   public JwtUsernameAndPasswordAuthenticationFilter(
            AuthenticationManager authenticationManager) {

        this.authenticationManager = authenticationManager;
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {

            UsernameAndPasswordAuthentication authRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthentication.class);


            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    authRequest.getFincode(),
                    authRequest.getPassword()
            );


            return authenticationManager.authenticate(authenticationToken);

        } catch (IOException ex) {
            throw new RuntimeException("Giriş məlumatları oxuna bilmədi", ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 24 * 14))//2 weeks token life
                .signWith(Keys.hmacShaKeyFor("supersecretkeythatshouldbeatleast32characterslong".getBytes()))// signature and secret imza
                .compact();

        response.setContentType("application/json");//Htpp resppnse bide oldugu ucun bunu veririk
        response.getWriter().write("{\"token\":\"" + token + "}");
    }
}

