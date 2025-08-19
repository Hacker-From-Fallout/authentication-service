package com.ignat.chernyshov.auth.producers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.ignat.chernyshov.auth.domain.dto.kafka.SellerProfileCreateDto;
import com.ignat.chernyshov.auth.domain.dto.kafka.UserEmailUpdateDto;
import com.ignat.chernyshov.auth.domain.dto.kafka.UserPhoneNumberUpdateDto;
import com.ignat.chernyshov.auth.domain.dto.kafka.UserUsernameUpdateDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SellerUserProducer {

    private final KafkaTemplate<String, Object> jsonKafkaTemplate;
    private final KafkaTemplate<String, Long> longKafkaTemplate;

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

    public void createProfile(SellerProfileCreateDto dto) {
        jsonKafkaTemplate.send(CREATE_PROFILE_SELLER_TOPIC, dto);
    }

    public void updateUsername(Long id, String newUsername) {
        UserUsernameUpdateDto dto = new UserUsernameUpdateDto(id, newUsername);
        jsonKafkaTemplate.send(UPDATE_USERNAME_SELLER_TOPIC, dto);
    }

    public void updateEmail(Long id, String newEmail) {
        UserEmailUpdateDto dto = new UserEmailUpdateDto(id, newEmail);
        jsonKafkaTemplate.send(UPDATE_EMAIL_SELLER_TOPIC, dto);
    }

    public void updatePhoneNumber(Long id, String newPhoneNumber) {
        UserPhoneNumberUpdateDto dto = new UserPhoneNumberUpdateDto(id, newPhoneNumber);
        jsonKafkaTemplate.send(UPDATE_PHONE_NUMBER_SELLER_TOPIC, dto);
    }

    public void deleteProfile(Long id) {
        longKafkaTemplate.send(DELETE_PROFILE_SELLER_TOPIC, id);
    }
}