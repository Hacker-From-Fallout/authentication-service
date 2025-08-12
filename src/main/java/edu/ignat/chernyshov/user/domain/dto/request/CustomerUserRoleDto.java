package edu.ignat.chernyshov.user.domain.dto.request;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserRole;

import jakarta.validation.constraints.NotNull;

public record CustomerUserRoleDto(
    @NotNull(message = "Role must be provided")
    CustomerUserRole role
) {}
