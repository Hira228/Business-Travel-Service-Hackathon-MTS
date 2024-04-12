package authservice.auth.kafka;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KafkaProducer {

    KafkaTemplate<String, String> kafkaTemplate;

    public void sendDataUserId(String token, UUID id){
        kafkaTemplate.send("user-id", token, id.toString());
    }

    public void updateDataUserId(String token){
        kafkaTemplate.send("user-id-update", token);
    }

}