package edu.ignat.chernyshov.user.domain.dto.request;

import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserAuthority;
import jakarta.validation.constraints.NotNull;

public record SellerUserAuthoritiesDto(
    @NotNull(message = "Authorities must be provided")
    EnumSet<SellerUserAuthority> authorities
) {}
