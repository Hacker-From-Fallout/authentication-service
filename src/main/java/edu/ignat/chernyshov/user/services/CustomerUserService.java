package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;

import org.springframework.data.domain.Page;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.CustomerUserRole;
import edu.ignat.chernyshov.user.domain.dto.request.CustomerUserCreateDto;
import edu.ignat.chernyshov.user.domain.dto.request.CustomerUserFilterDto;
import edu.ignat.chernyshov.user.domain.dto.request.CustomerUserUpdateDto;
import edu.ignat.chernyshov.user.domain.entities.CustomerUser;

public interface CustomerUserService {
    Page<CustomerUser> findByFilters(CustomerUserFilterDto filters, int page, int size);
    CustomerUser findById(Long id);
    CustomerUser findByUsername(String username);
    CustomerUser findByEmail(String email);
    CustomerUser findByPhoneNumber(String phoneNumber);
    
    CustomerUser createUser(CustomerUserCreateDto dto);
    CustomerUser updateUser(Long id, CustomerUserUpdateDto dto);

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

    void addRole(Long userId, CustomerUserRole role);
    void addRoles(Long userId, EnumSet<CustomerUserRole> roles);
    void removeRole(Long userId, CustomerUserRole role);
    void removeRoles(Long userId, EnumSet<CustomerUserRole> roles);

    void updateRoles(Long userId, EnumSet<CustomerUserRole> roles);

    void addAuthority(Long userId, CustomerUserAuthority authority);
    void addAuthorities(Long userId, EnumSet<CustomerUserAuthority> authorities);
    void removeAuthority(Long userId, CustomerUserAuthority authority);
    void removeAuthorities(Long userId, EnumSet<CustomerUserAuthority> authorities);

    void updateAuthorities(Long userId, EnumSet<CustomerUserAuthority> authorities);

    void deleteById(Long id);
}
