package az.banking.bankmanagementsystem.config;


import az.banking.bankmanagementsystem.dao.entity.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

    @Configuration
    public class RedisConfig {

            @Bean
            public RedisTemplate<String, Account> redisTemplate(RedisConnectionFactory connectionFactory) {
                RedisTemplate<String, Account> template = new RedisTemplate<>();
                template.setConnectionFactory(connectionFactory);
                return template;
            }
    }
