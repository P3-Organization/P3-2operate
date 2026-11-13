package dk.aau.sw3.p32operate.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApiConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("https://www.elprisenligenu.dk/api/v1/prices")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
