package com.mozahidone.openai.config;

import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class BeanConfig {

    @Value("${chat-gpt.token}")
    private String chatGptToken;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(chatGptToken, Duration.ofSeconds(180));
    }

}
