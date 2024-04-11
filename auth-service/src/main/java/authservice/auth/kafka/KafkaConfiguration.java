package authservice.auth.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic newTopicUserId() {
        return new NewTopic(
                "user-id",
                2,
                (short) 3
        );

    }

    @Bean
    public NewTopic newTopicUserIdtUpdate() {
        return new NewTopic(
                "user-id-update",
                2,
                (short) 3
        );

    }


}