package passportservice.passport.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import passportservice.passport.entity.Passport;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaProducer {
    KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper objectMapper;

    public void sendDataUser(Passport passport){
        try {
            String serializedData = objectMapper.writeValueAsString(passport);
            System.out.println(serializedData);
            kafkaTemplate.send("user-data", serializedData);
        } catch (JsonProcessingException e) {
            log.debug(e.getMessage());
        }
    }

}
