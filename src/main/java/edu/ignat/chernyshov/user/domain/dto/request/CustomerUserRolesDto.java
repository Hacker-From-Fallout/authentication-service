package edu.ignat.chernyshov.user.domain.dto.request;

import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserRole;
import jakarta.validation.constraints.NotNull;

public record CustomerUserRolesDto(
    @NotNull(message = "Roles must be provided")
    EnumSet<CustomerUserRole> roles
) {}
