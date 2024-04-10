package bookingservice.booking.kafka;

import bookingservice.booking.entity.ApprovedUser;
import bookingservice.booking.repository.ApprovedUserRepository;
import bookingservice.booking.repository.BookingRepository;
import bookingservice.booking.web.dto.ApprovedUserDTO;
import bookingservice.booking.web.mapper.ApprovedUserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class KafkaConsumer {

    ApprovedUserMapper approvedUserMapper;
    RedisTemplate<String, Object> redisTemplate;
    ApprovedUserRepository approvedUserRepository;
    ObjectMapper objectMapper;

    @KafkaListener(topics = "user-id", groupId = "user-id-group-1")
    public void listenerId(ConsumerRecord<String, String> data) {
        if (data.key() != null && data.value() != null) {
            redisTemplate.opsForValue().set(data.key(), data.value());
        } else {
            log.debug("'user-id' came up with zero values.");
        }
    }

    @KafkaListener(topics = "user-id-update", groupId = "user-id-group-1")
    public void listenerIdUpdate(String key) {
        redisTemplate.delete(key);
    }


    @KafkaListener(topics = "user-data", groupId = "user-data-group")
    public void listenerUserData(String userData) {

        try {
            System.out.println(userData + "!!!user DATA!!!");
            ApprovedUserDTO approvedUserDTO = objectMapper.readValue(userData, ApprovedUserDTO.class);

            System.out.println(approvedUserDTO + "!!!approvedUserDTO!!!");
            ApprovedUser approvedUser = approvedUserMapper.toEntity(approvedUserDTO);

            System.out.println(approvedUser + "!!!ApprovedUser!!!");
            approvedUserRepository.save(approvedUser);

        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }
}