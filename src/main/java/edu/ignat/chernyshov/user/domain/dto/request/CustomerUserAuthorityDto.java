package edu.ignat.chernyshov.user.domain.dto.request;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;
import jakarta.validation.constraints.NotNull;

public record CustomerUserAuthorityDto(
    @NotNull(message = "Authority must be provided")
    CustomerUserAuthority authority
) {}
