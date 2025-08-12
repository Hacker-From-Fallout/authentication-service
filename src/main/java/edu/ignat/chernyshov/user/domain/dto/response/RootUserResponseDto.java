package edu.ignat.chernyshov.user.domain.dto.response;

import java.time.LocalDateTime;
import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.RootUserRole;
import edu.ignat.chernyshov.user.domain.entities.RootUser;

public record RootUserResponseDto(
    Long id,
    String username,
    String email,
    String firstName,
    String lastName,
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
            rootUser.getUsername(),
            rootUser.getEmail(),
            rootUser.getFirstName(),
            rootUser.getLastName(),
            EnumSet.copyOf(rootUser.getRoles()),
            EnumSet.copyOf(rootUser.getAuthoritiesSet()),
            rootUser.isAccountNonExpired(),
            rootUser.isAccountNonLocked(),
            rootUser.isCredentialsNonExpired(),
            rootUser.isEnabled(),
            rootUser.getRegistrationDate(),
            rootUser.getLastLoginDate()
        );
    }
}