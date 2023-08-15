package com.mychatsocial.messageApi.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.concurrent.TimeUnit;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic topic1(){
        return TopicBuilder
                .name("chat")
                .partitions(2)
                .replicas(1)
                .config(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(TimeUnit.DAYS.toMillis(7)))
                .build();
    }
}
