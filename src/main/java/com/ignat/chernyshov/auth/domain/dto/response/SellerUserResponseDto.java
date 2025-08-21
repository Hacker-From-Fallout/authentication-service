package com.ignat.chernyshov.auth.domain.dto.response;

import java.time.LocalDateTime;
import java.util.EnumSet;

import com.ignat.chernyshov.auth.domain.authorities.SellerUserAuthority;
import com.ignat.chernyshov.auth.domain.authorities.SellerUserRole;
import com.ignat.chernyshov.auth.domain.entities.SellerUser;

public record SellerUserResponseDto(
    Long id,
    Long organizationId,
    String username,
    String email,
    String phoneNumber,
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
            sellerUser.getId(),
            sellerUser.getOrganizationId(),
            sellerUser.getUsername(),
            sellerUser.getEmail(),
            sellerUser.getPhoneNumber(),
            sellerUser.getRoles().isEmpty() ? EnumSet.noneOf(SellerUserRole.class) : EnumSet.copyOf(sellerUser.getRoles()),
            sellerUser.getAuthorities().isEmpty() ? EnumSet.noneOf(SellerUserAuthority.class) : EnumSet.copyOf(sellerUser.getAuthoritiesSet()),
            sellerUser.isAccountNonExpired(),
            sellerUser.isAccountNonLocked(),
            sellerUser.isCredentialsNonExpired(),
            sellerUser.isEnabled(),
            sellerUser.getRegistrationDate(),
            sellerUser.getLastLoginDate()
        );
    }
}