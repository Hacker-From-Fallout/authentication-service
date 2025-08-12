package edu.ignat.chernyshov.user.domain.dto.request;

import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;

import jakarta.validation.constraints.NotNull;

public record RootUserAuthoritiesDto(
    @NotNull(message = "Authoritis must be provided")
    EnumSet<RootUserAuthority> authorities
) {}
