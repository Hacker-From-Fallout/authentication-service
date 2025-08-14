package com.ignat.chernyshov.auth.domain.dto.request;

import java.util.EnumSet;

import com.ignat.chernyshov.auth.domain.authorities.SellerUserRole;

import jakarta.validation.constraints.NotNull;

public record SellerUserRolesDto(
    @NotNull(message = "Roles must be provided")
    EnumSet<SellerUserRole> roles
) {}
