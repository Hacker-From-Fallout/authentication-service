package edu.ignat.chernyshov.user.domain.dto.request;

import java.time.LocalDateTime;
import java.util.EnumSet;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.SellerUserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SellerUserUpdateDto(
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only letters")
    @Size(max = 50, message = "First name must be at most 50 characters")
    String firstName,

    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only letters")
    @Size(max = 50, message = "Last name must be at most 50 characters")
    String lastName,

    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    String username,

    @Email(message = "Email should be valid")
    String email,

    @Pattern(regexp = "^\\+?[0-9\\-\\s]{7,15}$", message = "Phone number is invalid")
    String phoneNumber,

    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
        message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character"
    )
    String password,

    EnumSet<SellerUserRole> roles,
    EnumSet<SellerUserAuthority> authorities,
    
    Boolean accountNonExpired,
    Boolean accountNonLocked,
    Boolean credentialsNonExpired,
    Boolean enabled,

    LocalDateTime lastLoginDate
) {}
