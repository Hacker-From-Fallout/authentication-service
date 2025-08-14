package com.ignat.chernyshov.auth.domain.dto.request;

import java.util.EnumSet;

import com.ignat.chernyshov.auth.domain.authorities.CustomerUserRole;

import jakarta.validation.constraints.NotNull;

public record CustomerUserRolesDto(
    @NotNull(message = "Roles must be provided")
    EnumSet<CustomerUserRole> roles
) {}
