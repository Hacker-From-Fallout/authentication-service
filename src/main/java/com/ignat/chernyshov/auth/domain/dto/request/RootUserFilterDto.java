package com.ignat.chernyshov.auth.domain.dto.request;

public record RootUserFilterDto(
    String firstName,
    String lastName,
    String username,
    String email,
    String phoneNumber
) {}
