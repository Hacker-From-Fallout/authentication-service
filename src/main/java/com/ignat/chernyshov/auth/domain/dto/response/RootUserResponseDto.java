package com.ignat.chernyshov.auth.domain.dto.response;

import java.time.LocalDateTime;
import java.util.EnumSet;

import com.ignat.chernyshov.auth.domain.authorities.RootUserAuthority;
import com.ignat.chernyshov.auth.domain.authorities.RootUserRole;
import com.ignat.chernyshov.auth.domain.entities.RootUser;

public record RootUserResponseDto(
    Long id,
    String firstName,
    String lastName,
    String username,
    String email,
    String phoneNumber,
    EnumSet<RootUserRole> roles,
    EnumSet<RootUserAuthority> authorities,
    Boolean accountNonExpired,
    Boolean accountNonLocked,
    Boolean credentialsNonExpired,
    Boolean enabled,
    LocalDateTime registrationDate,
    LocalDateTime lastLoginDate
) {
    public static RootUserResponseDto from(RootUser rootUser) {
        return new RootUserResponseDto(
            rootUser.getId(),
            rootUser.getFirstName(),
            rootUser.getLastName(),
            rootUser.getUsername(),
            rootUser.getEmail(),
            rootUser.getPhoneNumber(),
            rootUser.getRoles().isEmpty() ? EnumSet.noneOf(RootUserRole.class) : EnumSet.copyOf(rootUser.getRoles()),
            rootUser.getAuthorities().isEmpty() ? EnumSet.noneOf(RootUserAuthority.class) : EnumSet.copyOf(rootUser.getAuthoritiesSet()),
            rootUser.isAccountNonExpired(),
            rootUser.isAccountNonLocked(),
            rootUser.isCredentialsNonExpired(),
            rootUser.isEnabled(),
            rootUser.getRegistrationDate(),
            rootUser.getLastLoginDate()
        );
    }
}