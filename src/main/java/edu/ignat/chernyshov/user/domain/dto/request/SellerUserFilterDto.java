package edu.ignat.chernyshov.user.domain.dto.request;

public record SellerUserFilterDto(
    String firstName,
    String lastName,
    String username,
    String email,
    String phoneNumber
) {}
