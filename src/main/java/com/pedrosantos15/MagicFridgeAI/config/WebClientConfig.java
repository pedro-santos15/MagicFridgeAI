package com.pedrosantos15.MagicFridgeAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${gemini.base-url}")
    private String geminiApiUrl;

    @Value("${gemini.model}")
    private String model;



    @Bean
    public WebClient webClient(WebClient.Builder builder){
        return builder.baseUrl(geminiApiUrl + "/v1beta/models/" + model + ":generateContent").build();
    }
}
