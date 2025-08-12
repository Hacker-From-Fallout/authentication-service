package edu.ignat.chernyshov.user.domain.dto.request;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserAuthority;
import jakarta.validation.constraints.NotNull;

public record SellerUserAuthorityDto(
    @NotNull(message = "Authority must be provided")
    SellerUserAuthority authority
) {}
