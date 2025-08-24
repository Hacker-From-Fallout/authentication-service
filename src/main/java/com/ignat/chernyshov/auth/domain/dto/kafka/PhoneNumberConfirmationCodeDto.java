package com.ignat.chernyshov.auth.domain.dto.kafka;

public record PhoneNumberConfirmationCodeDto(
    String phoneNumber,
    String code
) {}