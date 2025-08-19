package com.ignat.chernyshov.auth.producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.ignat.chernyshov.auth.configs.KafkaProperties;
import com.ignat.chernyshov.auth.domain.dto.kafka.CustomerProfileCreateDto;
import com.ignat.chernyshov.auth.domain.dto.kafka.UserEmailUpdateDto;
import com.ignat.chernyshov.auth.domain.dto.kafka.UserPhoneNumberUpdateDto;
import com.ignat.chernyshov.auth.domain.dto.kafka.UserUsernameUpdateDto;

@Component
public class CustomerUserProducer {

    private final KafkaTemplate<String, Object> jsonKafkaTemplate;
    private final KafkaTemplate<String, Long> longKafkaTemplate;
    private final String CREATE_PROFILE_TOPIC;
    private final String UPDATE_USERNAME_TOPIC;
    private final String UPDATE_EMAIL_TOPIC;
    private final String UPDATE_PHONE_TOPIC;
    private final String DELETE_PROFILE_TOPIC;


    public CustomerUserProducer(KafkaProperties kafkaProperties,
                                KafkaTemplate<String, Object> jsonKafkaTemplate,
                                KafkaTemplate<String, Long> longKafkaTemplate) {

        this.jsonKafkaTemplate = jsonKafkaTemplate;
        this.longKafkaTemplate = longKafkaTemplate;

        this.CREATE_PROFILE_TOPIC = kafkaProperties.getCreateProfileTopic();
        this.UPDATE_USERNAME_TOPIC = kafkaProperties.getUpdateUsernameTopic();
        this.UPDATE_EMAIL_TOPIC = kafkaProperties.getUpdateEmailTopic();
        this.UPDATE_PHONE_TOPIC = kafkaProperties.getUpdatePhoneNumberTopic();
        this.DELETE_PROFILE_TOPIC = kafkaProperties.getDeleteProfileTopic();
    }

    public void createProfile(CustomerProfileCreateDto dto) {
        jsonKafkaTemplate.send(CREATE_PROFILE_TOPIC, dto);
    }

    public void updateUsername(Long id, String newUsername) {
        UserUsernameUpdateDto dto = new UserUsernameUpdateDto(id, newUsername);
        jsonKafkaTemplate.send(UPDATE_USERNAME_TOPIC, dto);
    }

    public void updateEmail(Long id, String newEmail) {
        UserEmailUpdateDto dto = new UserEmailUpdateDto(id, newEmail);
        jsonKafkaTemplate.send(UPDATE_EMAIL_TOPIC, dto);
    }

    public void updatePhoneNumber(Long id, String newPhoneNumber) {
        UserPhoneNumberUpdateDto dto = new UserPhoneNumberUpdateDto(id, newPhoneNumber);
        jsonKafkaTemplate.send(UPDATE_PHONE_TOPIC, dto);
}

    public void deleteProfile(Long id) {
        longKafkaTemplate.send(DELETE_PROFILE_TOPIC, id);
    }
}
