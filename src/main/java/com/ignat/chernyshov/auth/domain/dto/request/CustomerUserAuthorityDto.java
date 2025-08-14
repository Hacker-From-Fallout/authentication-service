package com.ignat.chernyshov.auth.domain.dto.request;

import com.ignat.chernyshov.auth.domain.authorities.CustomerUserAuthority;

import jakarta.validation.constraints.NotNull;

public record CustomerUserAuthorityDto(
    @NotNull(message = "Authority must be provided")
    CustomerUserAuthority authority
) {}
