package com.ignat.chernyshov.auth.domain.dto.request;

import java.util.EnumSet;

import com.ignat.chernyshov.auth.domain.authorities.SellerUserAuthority;

import jakarta.validation.constraints.NotNull;

public record SellerUserAuthoritiesDto(
    @NotNull(message = "Authorities must be provided")
    EnumSet<SellerUserAuthority> authorities
) {}
