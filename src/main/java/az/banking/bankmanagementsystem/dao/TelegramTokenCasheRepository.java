package az.banking.bankmanagementsystem.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.concurrent.TimeUnit;


@Slf4j
@Repository
@RequiredArgsConstructor
public class TelegramTokenCasheRepository {


    @Value("${cache.redis.telegram.ttl}")
    private Long ttlSeconds;
    private final RedisTemplate<String, Long>  redisTemplate;

    private static final String PREFIX = "telegram:link:";


    public void save(String token, Long authId) {
        redisTemplate.opsForValue()
                .set(PREFIX + token, authId, ttlSeconds, TimeUnit.SECONDS);

        log.info("Telegram token saved | token={} | ttl={}s", token, ttlSeconds);
    }

    //bu kod ile token verilir  ve auth id alinir.

    public Long consume(String token) {
        String key = PREFIX + token;

        Long authId = redisTemplate.opsForValue().get(key);

        if (authId != null) {
            redisTemplate.delete(key); // 1 dəfəlik
            log.info("Telegram token consumed | token={}", token);
        }

        return authId;
    }

}
