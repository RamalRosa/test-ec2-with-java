package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${beeceptor.base-url}")
    private String baseURL;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder().baseUrl(baseURL);
    }
}
