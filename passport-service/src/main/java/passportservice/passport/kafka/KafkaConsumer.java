package passportservice.passport.kafka;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class KafkaConsumer {

    RedisTemplate<String, Object> redisTemplate;

    @KafkaListener(topics = "user-id-passport", groupId = "user-id-passport-group")
    public void listenerId(ConsumerRecord<String, String> data) {
        if (data.key() != null && data.value() != null) {
            redisTemplate.opsForValue().set(data.key(), data.value());
        } else {
            log.debug("'user-id-passport' came up with zero values.");
        }
    }

    @KafkaListener(topics = "user-id-passport-update", groupId = "user-id-passport-group")
    public void listenerIdUpdate(String key) {
            redisTemplate.delete(key);
    }
}