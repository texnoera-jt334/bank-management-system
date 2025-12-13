package az.banking.bankmanagementsystem.dao;


import az.banking.bankmanagementsystem.dao.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
@RequiredArgsConstructor
//Service Transcion withdrawal -de tetbiq edek
public class AccountCasheRespository {

    private final RedisTemplate<String, Account> redisTemplate;


    @Value("${cache.redis.account.ttl}")
    Long ttl;

    public Account read(String accountNumber) {

        return redisTemplate.opsForValue().get(accountNumber);
    }

    public void save(Account account) {

        redisTemplate.opsForValue().set(account.getAccountNumber(), account, ttl, TimeUnit.SECONDS);
    }

}
