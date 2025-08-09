package edu.ignat.chernyshov.user.domain.dto;

import java.time.LocalDateTime;
import java.util.Set;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;

public record CustomerUserUpdateDto(
            Set<CustomerUserAuthority> authorities,
            Long id,
            String firstName,
            String lastName,
            String phoneNumber,
            String email,
            String username,
            String password,
            LocalDateTime lastLoginDate) {
}
