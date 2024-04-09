package authservice.auth.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class TokenExpirationChecker {

    private final RedisTemplate<String, Object> redisTemplate;
    private final JwtTokenUtils jwtTokenUtils;

    @Async
    @Scheduled(fixedRate = 60000)
    public void checkTokenExpiration() {

        Set<String> usernames = redisTemplate.keys("*");
        System.out.println("Checking token expiration...");
        for (String username : usernames) {
            String storedToken = (String) redisTemplate.opsForValue().get(username);
            if (storedToken == null) continue;
            if (!jwtTokenUtils.isValidToken(storedToken)) {
                redisTemplate.delete(username);
            }
        }
    }

}
