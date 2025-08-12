package edu.ignat.chernyshov.user.domain.dto.request;

import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;

import jakarta.validation.constraints.NotNull;

public record CustomerUserAuthoritiesDto(
    @NotNull(message = "Authorities must be provided")
    EnumSet<CustomerUserAuthority> authorities
) {}
