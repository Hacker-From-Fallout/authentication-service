package edu.ignat.chernyshov.user.domain.dto;

public record CustomerUserCreateDto(
            String firstName,
            String lastName,
            String phoneNumber,
            String email,
            String username,
            String password) {
}
