package gateway.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate template() {
        return new RestTemplate();
    }
}