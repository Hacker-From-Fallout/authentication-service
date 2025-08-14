package com.ignat.chernyshov.auth.domain.dto.request;

import com.ignat.chernyshov.auth.domain.authorities.RootUserAuthority;

import jakarta.validation.constraints.NotNull;

public record RootUserAuthorityDto(
    @NotNull(message = "Authority must be provided")
    RootUserAuthority authority
) {}
