package com.ignat.chernyshov.auth.domain.dto.kafka;

public record SellerProfileCreateDto(
    Long id,
    String firstName,
    String lastName,
    String username,
    String email,
    String phoneNumber
) {}
