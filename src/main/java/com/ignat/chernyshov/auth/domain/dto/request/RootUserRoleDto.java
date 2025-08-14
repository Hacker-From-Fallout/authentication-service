package com.ignat.chernyshov.auth.domain.dto.request;

import com.ignat.chernyshov.auth.domain.authorities.RootUserRole;

import jakarta.validation.constraints.NotNull;

public record RootUserRoleDto(
    @NotNull(message = "Role must be provided")
    RootUserRole role
) {}
