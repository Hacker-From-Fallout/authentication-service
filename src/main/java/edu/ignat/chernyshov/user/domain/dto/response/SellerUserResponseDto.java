package edu.ignat.chernyshov.user.domain.dto.response;

import java.time.LocalDateTime;
import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.SellerUserRole;
import edu.ignat.chernyshov.user.domain.entities.SellerUser;

public record SellerUserResponseDto(
    Long id,
    Long organizationId,
    String username,
    String email,
    String firstName,
    String lastName,
    EnumSet<SellerUserRole> roles,
    EnumSet<SellerUserAuthority> authorities,
    Boolean accountNonExpired,
    Boolean accountNonLocked,
    Boolean credentialsNonExpired,
    Boolean enabled,
    LocalDateTime registrationDate,
    LocalDateTime lastLoginDate
) {
    public static SellerUserResponseDto from(SellerUser sellerUser) {
        return new SellerUserResponseDto(
            sellerUser.getOrganizationId(),
            sellerUser.getId(),
            sellerUser.getUsername(),
            sellerUser.getEmail(),
            sellerUser.getFirstName(),
            sellerUser.getLastName(),
            EnumSet.copyOf(sellerUser.getRoles()),
            EnumSet.copyOf(sellerUser.getAuthoritiesSet()),
            sellerUser.isAccountNonExpired(),
            sellerUser.isAccountNonLocked(),
            sellerUser.isCredentialsNonExpired(),
            sellerUser.isEnabled(),
            sellerUser.getRegistrationDate(),
            sellerUser.getLastLoginDate()
        );
    }
}