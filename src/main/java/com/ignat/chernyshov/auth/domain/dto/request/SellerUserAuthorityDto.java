package com.ignat.chernyshov.auth.domain.dto.request;

import com.ignat.chernyshov.auth.domain.authorities.SellerUserAuthority;

import jakarta.validation.constraints.NotNull;

public record SellerUserAuthorityDto(
    @NotNull(message = "Authority must be provided")
    SellerUserAuthority authority
) {}
