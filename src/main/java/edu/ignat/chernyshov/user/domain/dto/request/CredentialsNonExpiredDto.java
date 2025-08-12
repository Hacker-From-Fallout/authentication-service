package edu.ignat.chernyshov.user.domain.dto.request;

import jakarta.validation.constraints.NotNull;

public record CredentialsNonExpiredDto(
    @NotNull(message = "Credentials non-expired status must be specified")
    Boolean credentialsNonExpired
) {}
