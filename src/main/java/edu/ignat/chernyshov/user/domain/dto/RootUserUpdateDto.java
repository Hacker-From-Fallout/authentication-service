package edu.ignat.chernyshov.user.domain.dto;

import java.time.LocalDateTime;
import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.CustomerUserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RootUserUpdateDto(
    @NotNull(message = "ID must be provided")
    Long id,

    EnumSet<CustomerUserRole> roles,
    EnumSet<CustomerUserAuthority> authorities,
    
    Boolean accountNonExpired,
    Boolean accountNonLocked,
    Boolean credentialsNonExpired,
    Boolean enabled,

    @Size(max = 50, message = "First name must be at most 50 characters")
    String firstName,

    @Size(max = 50, message = "Last name must be at most 50 characters")
    String lastName,

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    String username,

    @Email(message = "Email should be valid")
    String email,

    @Pattern(regexp = "^\\+?[0-9\\-\\s]{7,15}$", message = "Phone number is invalid")
    String phoneNumber,

    @Size(min = 8, message = "Password must be at least 8 characters")
    String password,

    LocalDateTime lastLoginDate
) {}
