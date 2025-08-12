package edu.ignat.chernyshov.user.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PhoneNumberDto(
    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]{7,15}$", message = "Phone number is invalid")
    String phoneNumber
) {}
