package com.ignat.chernyshov.auth.domain.dto.request;

import com.ignat.chernyshov.auth.domain.authorities.CustomerUserRole;

import jakarta.validation.constraints.NotNull;

public record CustomerUserRoleDto(
    @NotNull(message = "Role must be provided")
    CustomerUserRole role
) {}
