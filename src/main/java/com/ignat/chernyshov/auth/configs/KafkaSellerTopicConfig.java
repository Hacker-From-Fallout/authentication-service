package com.ignat.chernyshov.auth.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaSellerTopicConfig {

    @Value("${spring.kafka.topics.seller.create-profile}")
    private String CREATE_PROFILE_SELLER_TOPIC;

    @Value("${spring.kafka.topics.seller.update-username}")
    private String UPDATE_USERNAME_SELLER_TOPIC;

    @Value("${spring.kafka.topics.seller.update-email}")
    private String UPDATE_EMAIL_SELLER_TOPIC;

    @Value("${spring.kafka.topics.seller.update-phone-number}")
    private String UPDATE_PHONE_NUMBER_SELLER_TOPIC;

    @Value("${spring.kafka.topics.seller.delete-profile}")
    private String DELETE_PROFILE_SELLER_TOPIC;

    @Bean
    public NewTopic createSellerProfileTopic() {
        return TopicBuilder.name(CREATE_PROFILE_SELLER_TOPIC)
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
    public NewTopic updateSellerUsernameTopic() {
        return TopicBuilder.name(UPDATE_USERNAME_SELLER_TOPIC)
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
    public NewTopic updateSellerEmailTopic() {
        return TopicBuilder.name(UPDATE_EMAIL_SELLER_TOPIC)
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
    public NewTopic updateSellerPhoneTopic() {
        return TopicBuilder.name(UPDATE_PHONE_NUMBER_SELLER_TOPIC)
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
    public NewTopic deleteSellerProfileTopic() {
        return TopicBuilder.name(DELETE_PROFILE_SELLER_TOPIC)
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


