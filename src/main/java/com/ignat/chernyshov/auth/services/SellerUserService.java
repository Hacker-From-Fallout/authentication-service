package com.ignat.chernyshov.auth.services;

import java.time.LocalDateTime;
import java.util.EnumSet;

import org.springframework.data.domain.Page;

import com.ignat.chernyshov.auth.domain.authorities.SellerUserAuthority;
import com.ignat.chernyshov.auth.domain.authorities.SellerUserRole;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserCreateDto;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserFilterDto;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserUpdateDto;
import com.ignat.chernyshov.auth.domain.entities.SellerUser;

public interface SellerUserService {
    Page<SellerUser> findByFilters(SellerUserFilterDto filters, int page, int size);
    SellerUser findById(Long id);
    SellerUser findByUsername(String username);
    SellerUser findByEmail(String email);
    SellerUser findByPhoneNumber(String phoneNumber);

    SellerUser createUser(SellerUserCreateDto dto);
    SellerUser updateUser(Long id, SellerUserUpdateDto dto);

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

    void addRole(Long userId, SellerUserRole role);
    void addRoles(Long userId, EnumSet<SellerUserRole> roles);
    void removeRole(Long userId, SellerUserRole role);
    void removeRoles(Long userId, EnumSet<SellerUserRole> roles);

    void updateRoles(Long userId, EnumSet<SellerUserRole> roles);

    void addAuthority(Long userId, SellerUserAuthority authority);
    void addAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities);
    void removeAuthority(Long userId, SellerUserAuthority authority);
    void removeAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities);

    void updateAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities);

    void deleteById(Long id);
}
