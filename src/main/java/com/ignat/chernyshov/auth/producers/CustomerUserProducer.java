package com.ignat.chernyshov.auth.producers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.ignat.chernyshov.auth.domain.dto.kafka.CustomerProfileCreateDto;
import com.ignat.chernyshov.auth.domain.dto.kafka.UserEmailUpdateDto;
import com.ignat.chernyshov.auth.domain.dto.kafka.UserPhoneNumberUpdateDto;
import com.ignat.chernyshov.auth.domain.dto.kafka.UserUsernameUpdateDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomerUserProducer {

    private final KafkaTemplate<String, Object> jsonKafkaTemplate;
    private final KafkaTemplate<String, Long> longKafkaTemplate;

    @Value("${spring.kafka.topics.customer.create-profile}")
    private String CREATE_PROFILE_CUSTOMER_TOPIC;

    @Value("${spring.kafka.topics.customer.update-username}")
    private String UPDATE_USERNAME_CUSTOMER_TOPIC;

    @Value("${spring.kafka.topics.customer.update-email}")
    private String UPDATE_EMAIL_CUSTOMER_TOPIC;

    @Value("${spring.kafka.topics.customer.update-phone-number}")
    private String UPDATE_PHONE_NUMBER_CUSTOMER_TOPIC;

    @Value("${spring.kafka.topics.customer.delete-profile}")
    private String DELETE_PROFILE_CUSTOMER_TOPIC;

    public void createProfile(CustomerProfileCreateDto dto) {
        jsonKafkaTemplate.send(CREATE_PROFILE_CUSTOMER_TOPIC, dto);
    }

    public void updateUsername(Long id, String username) {
        UserUsernameUpdateDto dto = new UserUsernameUpdateDto(id, username);
        jsonKafkaTemplate.send(UPDATE_USERNAME_CUSTOMER_TOPIC, dto);
    }

    public void updateEmail(Long id, String email) {
        UserEmailUpdateDto dto = new UserEmailUpdateDto(id, email);
        jsonKafkaTemplate.send(UPDATE_EMAIL_CUSTOMER_TOPIC, dto);
    }

    public void updatePhoneNumber(Long id, String phoneNumber) {
        UserPhoneNumberUpdateDto dto = new UserPhoneNumberUpdateDto(id, phoneNumber);
        jsonKafkaTemplate.send(UPDATE_PHONE_NUMBER_CUSTOMER_TOPIC, dto);
    }

    public void deleteProfile(Long id) {
        longKafkaTemplate.send(DELETE_PROFILE_CUSTOMER_TOPIC, id);
    }
}
