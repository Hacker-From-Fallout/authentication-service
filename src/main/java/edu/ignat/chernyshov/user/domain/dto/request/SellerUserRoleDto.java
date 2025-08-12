package edu.ignat.chernyshov.user.domain.dto.request;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserRole;
import jakarta.validation.constraints.NotNull;

public record SellerUserRoleDto(
    @NotNull(message = "Role must be provided")
    SellerUserRole role
) {}
