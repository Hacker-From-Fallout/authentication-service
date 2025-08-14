package com.ignat.chernyshov.auth.services;

import java.time.LocalDateTime;
import java.util.EnumSet;

import org.springframework.data.domain.Page;

import com.ignat.chernyshov.auth.domain.authorities.RootUserAuthority;
import com.ignat.chernyshov.auth.domain.authorities.RootUserRole;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserCreateDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserFilterDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserUpdateDto;
import com.ignat.chernyshov.auth.domain.entities.RootUser;

public interface RootUserService {
    Page<RootUser> findByFilters(RootUserFilterDto filters, int page, int size);
    RootUser findById(Long id);
    RootUser findByUsername(String username);
    RootUser findByEmail(String email);
    RootUser findByPhoneNumber(String phoneNumber);
    
    RootUser createUser(RootUserCreateDto dto);
    RootUser updateUser(Long id, RootUserUpdateDto dto);

    void updateFirstName(Long id, String firstName);
    void updateLastName(Long id, String lastName);
    void updateUsername(Long id, String username);
    void updateEmail(Long id, String email);
    void updatePhoneNumber(Long id, String phoneNumber);
    void updateHashPassword(Long id, String hashPassword);
    void updateLastLoginDate(Long id, LocalDateTime lastLoginDate);

    void updateAccountNonExpired(Long id, boolean accountNonExpired);
    void updateAccountNonLocked(Long id, boolean accountNonLocked);
    void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired);
    void updateEnabled(Long id, boolean enabled);

    void addRole(Long userId, RootUserRole role);
    void addRoles(Long userId, EnumSet<RootUserRole> roles);
    void removeRole(Long userId, RootUserRole role);
    void removeRoles(Long userId, EnumSet<RootUserRole> roles);

    void updateRoles(Long userId, EnumSet<RootUserRole> roles);

    void addAuthority(Long userId, RootUserAuthority authority);
    void addAuthorities(Long userId, EnumSet<RootUserAuthority> authorities);
    void removeAuthority(Long userId, RootUserAuthority authority);
    void removeAuthorities(Long userId, EnumSet<RootUserAuthority> authorities);

    void updateAuthorities(Long userId, EnumSet<RootUserAuthority> authorities);

    void deleteById(Long id);
}
