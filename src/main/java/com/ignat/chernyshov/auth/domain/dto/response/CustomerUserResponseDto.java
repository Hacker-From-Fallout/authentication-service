package com.ignat.chernyshov.auth.domain.dto.response;

import java.time.LocalDateTime;
import java.util.EnumSet;

import com.ignat.chernyshov.auth.domain.authorities.CustomerUserAuthority;
import com.ignat.chernyshov.auth.domain.authorities.CustomerUserRole;
import com.ignat.chernyshov.auth.domain.entities.CustomerUser;

public record CustomerUserResponseDto(
    Long id,
    String username,
    String email,
    String phoneNumber,
    EnumSet<CustomerUserRole> roles,
    EnumSet<CustomerUserAuthority> authorities,
    Boolean accountNonExpired,
    Boolean accountNonLocked,
    Boolean credentialsNonExpired,
    Boolean enabled,
    LocalDateTime registrationDate,
    LocalDateTime lastLoginDate
) {
    public static CustomerUserResponseDto from(CustomerUser customerUser) {
        return new CustomerUserResponseDto(
            customerUser.getId(),
            customerUser.getUsername(),
            customerUser.getEmail(),
            customerUser.getPhoneNumber(),
            customerUser.getRoles().isEmpty() ? EnumSet.noneOf(CustomerUserRole.class) : EnumSet.copyOf(customerUser.getRoles()),
            customerUser.getAuthorities().isEmpty() ? EnumSet.noneOf(CustomerUserAuthority.class) : EnumSet.copyOf(customerUser.getAuthoritiesSet()),
            customerUser.isAccountNonExpired(),
            customerUser.isAccountNonLocked(),
            customerUser.isCredentialsNonExpired(),
            customerUser.isEnabled(),
            customerUser.getRegistrationDate(),
            customerUser.getLastLoginDate()
        );
    }
}
