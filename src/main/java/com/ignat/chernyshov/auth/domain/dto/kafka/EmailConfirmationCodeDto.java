package com.ignat.chernyshov.auth.domain.dto.kafka;

public record EmailConfirmationCodeDto(
    String email,
    String code
) {}
