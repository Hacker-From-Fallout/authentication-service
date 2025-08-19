package com.ignat.chernyshov.auth.domain.dto.kafka;

public record UserPhoneNumberUpdateDto(
    Long id,
    String phoneNumber
) {}
