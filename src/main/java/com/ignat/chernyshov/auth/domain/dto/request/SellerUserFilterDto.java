package com.ignat.chernyshov.auth.domain.dto.request;

public record SellerUserFilterDto(
    String username,
    String email,
    String phoneNumber
) {}
