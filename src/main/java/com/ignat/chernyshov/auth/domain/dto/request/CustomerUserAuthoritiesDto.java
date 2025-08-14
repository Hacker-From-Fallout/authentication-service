package com.ignat.chernyshov.auth.domain.dto.request;

import java.util.EnumSet;

import com.ignat.chernyshov.auth.domain.authorities.CustomerUserAuthority;

import jakarta.validation.constraints.NotNull;

public record CustomerUserAuthoritiesDto(
    @NotNull(message = "Authorities must be provided")
    EnumSet<CustomerUserAuthority> authorities
) {}
