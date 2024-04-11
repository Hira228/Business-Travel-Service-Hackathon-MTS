package authservice.auth.utils;

import authservice.auth.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
@Slf4j
public class TokenExpirationChecker {
    private final KafkaProducer kafkaProducer;
    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtTokenUtils jwtTokenUtils;

    @Async
    @Scheduled(fixedRate = 60000)
    public void checkTokenExpiration() {

        Set<String> usernames = redisTemplate.keys("*");

        log.debug("Checking token expiration...");

        for (String username : usernames) {
            String storedToken = (String) redisTemplate.opsForValue().get(username);
            if (storedToken == null) continue;
            if (!jwtTokenUtils.isValidToken(storedToken)) {
                redisTemplate.delete(username);
                kafkaProducer.updateDataUserId(storedToken);
            }
        }
    }

}
