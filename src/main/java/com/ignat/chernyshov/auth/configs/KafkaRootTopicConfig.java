package com.ignat.chernyshov.auth.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaRootTopicConfig {

    @Value("${spring.kafka.topics.root.create-profile}")
    private String CREATE_PROFILE_ROOT_TOPIC;

    @Value("${spring.kafka.topics.root.update-username}")
    private String UPDATE_USERNAME_ROOT_TOPIC;

    @Value("${spring.kafka.topics.root.update-email}")
    private String UPDATE_EMAIL_ROOT_TOPIC;

    @Value("${spring.kafka.topics.root.update-phone-number}")
    private String UPDATE_PHONE_NUMBER_ROOT_TOPIC;

    @Value("${spring.kafka.topics.root.delete-profile}")
    private String DELETE_PROFILE_ROOT_TOPIC;

    @Bean
    public NewTopic createRootProfileTopic() {
        return TopicBuilder.name(CREATE_PROFILE_ROOT_TOPIC)
            .partitions(3)
            .replicas((short) 3)    
            .config("retention.ms", String.valueOf(7 * 24 * 60 * 60 * 1000L)) 
            .config("retention.bytes", "1073741824") 
            .config("segment.bytes", "1073741824") 
            .config("segment.ms", "604800000")
            .config("cleanup.policy", "delete")
            .config("min.insync.replicas", "1")
            .build();
    }

    @Bean
    public NewTopic updateRootUsernameTopic() {
        return TopicBuilder.name(UPDATE_USERNAME_ROOT_TOPIC)
            .partitions(3)
            .replicas((short) 3)    
            .config("retention.ms", String.valueOf(7 * 24 * 60 * 60 * 1000L)) 
            .config("retention.bytes", "1073741824") 
            .config("segment.bytes", "1073741824") 
            .config("segment.ms", "604800000")
            .config("cleanup.policy", "delete")
            .config("min.insync.replicas", "1")
            .build();
    }

    @Bean
    public NewTopic updateRootEmailTopic() {
        return TopicBuilder.name(UPDATE_EMAIL_ROOT_TOPIC)
            .partitions(3)
            .replicas((short) 3)    
            .config("retention.ms", String.valueOf(7 * 24 * 60 * 60 * 1000L)) 
            .config("retention.bytes", "1073741824") 
            .config("segment.bytes", "1073741824") 
            .config("segment.ms", "604800000")
            .config("cleanup.policy", "delete")
            .config("min.insync.replicas", "1")
            .build();
    }

    @Bean
    public NewTopic updateRootPhoneTopic() {
        return TopicBuilder.name(UPDATE_PHONE_NUMBER_ROOT_TOPIC)
            .partitions(3)
            .replicas((short) 3)    
            .config("retention.ms", String.valueOf(7 * 24 * 60 * 60 * 1000L)) 
            .config("retention.bytes", "1073741824") 
            .config("segment.bytes", "1073741824") 
            .config("segment.ms", "604800000")
            .config("cleanup.policy", "delete")
            .config("min.insync.replicas", "1")
            .build();
    }

    @Bean
    public NewTopic deleteRootProfileTopic() {
        return TopicBuilder.name(DELETE_PROFILE_ROOT_TOPIC)
            .partitions(3)
            .replicas((short) 3)    
            .config("retention.ms", String.valueOf(7 * 24 * 60 * 60 * 1000L)) 
            .config("retention.bytes", "1073741824") 
            .config("segment.bytes", "1073741824") 
            .config("segment.ms", "604800000")
            .config("cleanup.policy", "delete")
            .config("min.insync.replicas", "1")
            .build();
    }
}

