package edu.ignat.chernyshov.user.domain.dto;

import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.CustomerUserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RootUserCreateDto(
    @NotNull(message = "Roles must be provided")
    EnumSet<CustomerUserRole> roles,

    @NotNull(message = "Authorities must be provided")
    EnumSet<CustomerUserAuthority> authorities,

    @NotNull(message = "Account non-expired status must be specified")
    Boolean accountNonExpired,

    @NotNull(message = "Account non-locked status must be specified")
    Boolean accountNonLocked,

    @NotNull(message = "Credentials non-expired status must be specified")
    Boolean credentialsNonExpired,

    @NotNull(message = "Enabled status must be specified")
    Boolean enabled,

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be at most 50 characters")
    String firstName,

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    String lastName,

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    String username,

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email,

    @Pattern(regexp = "^\\+?[0-9\\-\\s]{7,15}$", message = "Phone number is invalid")
    String phoneNumber,

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    String password
) {}
