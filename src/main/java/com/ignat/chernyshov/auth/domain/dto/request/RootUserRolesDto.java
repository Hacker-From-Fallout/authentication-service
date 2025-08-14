package com.ignat.chernyshov.auth.domain.dto.request;

import java.util.EnumSet;

import com.ignat.chernyshov.auth.domain.authorities.RootUserRole;

import jakarta.validation.constraints.NotNull;

public record RootUserRolesDto(
    @NotNull(message = "Roles must be provided")
    EnumSet<RootUserRole> roles
) {}
