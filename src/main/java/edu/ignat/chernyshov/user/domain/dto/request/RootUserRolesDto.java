package edu.ignat.chernyshov.user.domain.dto.request;

import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.RootUserRole;
import jakarta.validation.constraints.NotNull;

public record RootUserRolesDto(
    @NotNull(message = "Roles must be provided")
    EnumSet<RootUserRole> roles
) {}
