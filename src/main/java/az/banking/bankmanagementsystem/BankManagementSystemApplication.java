package az.banking.bankmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BankManagementSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(BankManagementSystemApplication.class, args);

    }

}
