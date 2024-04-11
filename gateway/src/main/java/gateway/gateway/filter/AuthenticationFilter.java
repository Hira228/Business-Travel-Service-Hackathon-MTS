package gateway.gateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private RestTemplate restTemplate;


    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {


        return (((exchange, chain) -> {
            try {
                String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

                HttpHeaders headers = new HttpHeaders();
                headers.set(HttpHeaders.AUTHORIZATION, authHeader);

                String path = exchange.getRequest().getURI().getPath();
                ResponseEntity<?> responseEntity = null;

                if(path.contains("/admin/")) {
                    responseEntity = restTemplate.exchange(
                            "http://localhost:8000/auth/admin",
                            HttpMethod.GET,
                            new HttpEntity<>(headers),
                            ResponseEntity.class
                    );
                }
                else {
                    responseEntity = restTemplate.exchange(
                            "http://localhost:8000/auth/validate",
                            HttpMethod.GET,
                            new HttpEntity<>(headers),
                            ResponseEntity.class
                    );
                }

                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    System.out.println("User accepted");
                    return chain.filter(exchange);
                } else {
                    System.out.println("User not authorized");
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }
            } catch (HttpClientErrorException.Unauthorized e) {
                System.out.println("Unauthorized access: " + e.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            } catch (Exception e) {
                System.out.println("Unexpected error occurred: " + e.getMessage());
                throw new RuntimeException("Unexpected error occurred", e);
            }
        }));
    }


    public static class Config {

    }
}