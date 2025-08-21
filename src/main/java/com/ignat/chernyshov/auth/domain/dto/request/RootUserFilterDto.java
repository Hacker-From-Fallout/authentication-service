package com.ignat.chernyshov.auth.domain.dto.request;

public record RootUserFilterDto(
    String username,
    String email,
    String phoneNumber
) {}
