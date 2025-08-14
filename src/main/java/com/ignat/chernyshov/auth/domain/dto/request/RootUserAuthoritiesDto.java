package com.ignat.chernyshov.auth.domain.dto.request;

import java.util.EnumSet;

import com.ignat.chernyshov.auth.domain.authorities.RootUserAuthority;

import jakarta.validation.constraints.NotNull;

public record RootUserAuthoritiesDto(
    @NotNull(message = "Authoritis must be provided")
    EnumSet<RootUserAuthority> authorities
) {}
