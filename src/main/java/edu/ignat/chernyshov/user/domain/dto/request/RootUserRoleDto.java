package edu.ignat.chernyshov.user.domain.dto.request;

import edu.ignat.chernyshov.user.domain.authorities.RootUserRole;
import jakarta.validation.constraints.NotNull;

public record RootUserRoleDto(
    @NotNull(message = "Role must be provided")
    RootUserRole role
) {}
