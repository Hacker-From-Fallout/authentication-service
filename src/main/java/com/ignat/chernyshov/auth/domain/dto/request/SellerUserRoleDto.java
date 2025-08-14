package com.ignat.chernyshov.auth.domain.dto.request;

import com.ignat.chernyshov.auth.domain.authorities.SellerUserRole;

import jakarta.validation.constraints.NotNull;

public record SellerUserRoleDto(
    @NotNull(message = "Role must be provided")
    SellerUserRole role
) {}
