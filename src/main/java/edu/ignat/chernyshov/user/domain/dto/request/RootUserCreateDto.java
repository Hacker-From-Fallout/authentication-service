package edu.ignat.chernyshov.user.domain.dto.request;

import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.RootUserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RootUserCreateDto(
    @NotBlank(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only letters")
    @Size(max = 50, message = "First name must be at most 50 characters")
    String firstName,

    @NotBlank(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only letters")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    String lastName,

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    String username,

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    String email,

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[0-9\\-\\s]{7,15}$", message = "Phone number is invalid")
    String phoneNumber,

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
        message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character"
    )
    String password,

    @NotNull(message = "Roles must be provided")
    EnumSet<RootUserRole> roles,

    @NotNull(message = "Authorities must be provided")
    EnumSet<RootUserAuthority> authorities,

    @NotNull(message = "Account non-expired status must be specified")
    Boolean accountNonExpired,

    @NotNull(message = "Account non-locked status must be specified")
    Boolean accountNonLocked,

    @NotNull(message = "Credentials non-expired status must be specified")
    Boolean credentialsNonExpired,

    @NotNull(message = "Enabled status must be specified")
    Boolean enabled
) {}
