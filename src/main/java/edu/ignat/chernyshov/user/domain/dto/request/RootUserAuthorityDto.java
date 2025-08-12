package edu.ignat.chernyshov.user.domain.dto.request;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;
import jakarta.validation.constraints.NotNull;

public record RootUserAuthorityDto(
    @NotNull(message = "Authority must be provided")
    RootUserAuthority authority
) {}
