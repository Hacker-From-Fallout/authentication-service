package edu.ignat.chernyshov.user.domain.dto.request;

import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserRole;
import jakarta.validation.constraints.NotNull;

public record SellerUserRolesDto(
    @NotNull(message = "Roles must be provided")
    EnumSet<SellerUserRole> roles
) {}
