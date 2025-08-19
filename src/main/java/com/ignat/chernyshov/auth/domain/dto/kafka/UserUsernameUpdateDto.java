package com.ignat.chernyshov.auth.domain.dto.kafka;

public record UserUsernameUpdateDto(
    Long id,
    String username
) {}
