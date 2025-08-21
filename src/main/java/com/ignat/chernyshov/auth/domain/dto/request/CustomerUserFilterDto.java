package com.ignat.chernyshov.auth.domain.dto.request;

public record CustomerUserFilterDto(
    String username,
    String email,
    String phoneNumber
) {}
