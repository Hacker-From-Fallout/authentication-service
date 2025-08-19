package com.ignat.chernyshov.auth.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    private final String CREATE_PROFILE_TOPIC;
    private final String UPDATE_USERNAME_TOPIC;
    private final String UPDATE_EMAIL_TOPIC;
    private final String UPDATE_PHONE_NUMBER_TOPIC;
    private final String DELETE_PROFILE_TOPIC;

    public KafkaTopicConfig(KafkaProperties kafkaProperties) {
        this.CREATE_PROFILE_TOPIC = kafkaProperties.getCreateProfileTopic();
        this.UPDATE_USERNAME_TOPIC = kafkaProperties.getUpdateUsernameTopic();
        this.UPDATE_EMAIL_TOPIC = kafkaProperties.getUpdateEmailTopic();
        this.UPDATE_PHONE_NUMBER_TOPIC = kafkaProperties.getUpdatePhoneNumberTopic();
        this.DELETE_PROFILE_TOPIC = kafkaProperties.getDeleteProfileTopic();
    }

    @Bean
    public NewTopic createProfileTopic() {
        return TopicBuilder.name(CREATE_PROFILE_TOPIC)
            .partitions(10)
            .replicas(3)    
            .config("retention.ms", String.valueOf(7 * 24 * 60 * 60 * 1000L)) 
            .config("retention.bytes", "1073741824") 
            .config("segment.bytes", "1073741824") 
            .config("segment.ms", "604800000")
            .config("cleanup.policy", "delete")
            .config("min.insync.replicas", "2")
            .build();
    }

    @Bean
    public NewTopic updateUsernameTopic() {
        return TopicBuilder.name(UPDATE_USERNAME_TOPIC)
                .partitions(10)
                .replicas(3)    
                .config("retention.ms", String.valueOf(7 * 24 * 60 * 60 * 1000L)) 
                .config("retention.bytes", "1073741824") 
                .config("segment.bytes", "1073741824") 
                .config("segment.ms", "604800000")
                .config("cleanup.policy", "delete")
                .config("min.insync.replicas", "2")
                .build();
        }

    @Bean
    public NewTopic updateEmailTopic() {
        return TopicBuilder.name(UPDATE_EMAIL_TOPIC)
                .partitions(10)
                .replicas(3)    
                .config("retention.ms", String.valueOf(7 * 24 * 60 * 60 * 1000L)) 
                .config("retention.bytes", "1073741824") 
                .config("segment.bytes", "1073741824") 
                .config("segment.ms", "604800000")
                .config("cleanup.policy", "delete")
                .config("min.insync.replicas", "2")
                .build();
        }

    @Bean
    public NewTopic updatePhoneTopic() {
        return TopicBuilder.name(UPDATE_PHONE_NUMBER_TOPIC)
                .partitions(10)
                .replicas(3)    
                .config("retention.ms", String.valueOf(7 * 24 * 60 * 60 * 1000L)) 
                .config("retention.bytes", "1073741824") 
                .config("segment.bytes", "1073741824") 
                .config("segment.ms", "604800000")
                .config("cleanup.policy", "delete")
                .config("min.insync.replicas", "2")
                .build();
        }

    @Bean
    public NewTopic deleteProfileTopic() {
        return TopicBuilder.name(DELETE_PROFILE_TOPIC)
                .partitions(10)
                .replicas(1)    
                .config("retention.ms", String.valueOf(7 * 24 * 60 * 60 * 1000L)) 
                .config("retention.bytes", "1073741824") 
                .config("segment.bytes", "1073741824") 
                .config("segment.ms", "604800000")
                .config("cleanup.policy", "delete")
                .config("min.insync.replicas", "2")
                .build();
        }
}
