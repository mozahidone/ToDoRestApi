package com.mozahidone.microservice.account.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {
  @Bean
  public NewTopic complianceIntegrationEventTopic() {
    return TopicBuilder.name("TEST_TOPIC").partitions(10).replicas(1).build();
  }
}