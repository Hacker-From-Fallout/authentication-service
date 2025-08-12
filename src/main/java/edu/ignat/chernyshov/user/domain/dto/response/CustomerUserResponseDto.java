package edu.ignat.chernyshov.user.domain.dto.response;

import java.time.LocalDateTime;
import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.CustomerUserRole;
import edu.ignat.chernyshov.user.domain.entities.CustomerUser;

public record CustomerUserResponseDto(
    Long id,
    String username,
    String email,
    String firstName,
    String lastName,
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
            customerUser.getFirstName(),
            customerUser.getLastName(),
            EnumSet.copyOf(customerUser.getRoles()),
            EnumSet.copyOf(customerUser.getAuthoritiesSet()),
            customerUser.isAccountNonExpired(),
            customerUser.isAccountNonLocked(),
            customerUser.isCredentialsNonExpired(),
            customerUser.isEnabled(),
            customerUser.getRegistrationDate(),
            customerUser.getLastLoginDate()
        );
    }
}
