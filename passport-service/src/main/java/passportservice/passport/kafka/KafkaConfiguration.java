package passportservice.passport.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    public NewTopic newTopicUserData() {
        return new NewTopic(
                "user-data",
                1,
                (short) 1
        );

    }
}
