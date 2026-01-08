package az.banking.bankmanagementsystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    public RestTemplate  restTemplate(){
    return new RestTemplate();
    }
}
