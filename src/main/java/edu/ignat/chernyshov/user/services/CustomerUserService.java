package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.CustomerUserRole;
import edu.ignat.chernyshov.user.domain.dto.request.CustomerUserCreateDto;
import edu.ignat.chernyshov.user.domain.dto.request.CustomerUserUpdateDto;
import edu.ignat.chernyshov.user.domain.entities.CustomerUser;

public interface CustomerUserService {
    public List<CustomerUser> findAll();

    public CustomerUser findById(Long id);
    public CustomerUser findByEmail(String email);
    public CustomerUser findByPhoneNumber(String phoneNumber);
    public CustomerUser findByUsername(String username);

    public CustomerUser createUser(CustomerUserCreateDto dto);
    public CustomerUser updateUser(Long id, CustomerUserUpdateDto dto);

    public void updateFirstName(Long id, String firstName);
    public void updateLastName(Long id, String lastName);
    public void updatePhoneNumber(Long id, String phoneNumber);
    public void updateEmail(Long id, String email);
    public void updateUsername(Long id, String username);
    public void updateHashPassword(Long id, String hashPassword);
    public void updateLastLoginDate(Long id, LocalDateTime lastLoginDate);
    public void updateRoles(Long id, EnumSet<CustomerUserRole> roles);
    public void updateAuthorities(Long id, EnumSet<CustomerUserAuthority> authorities);
    public void updateAccountNonExpired(Long id, boolean accountNonExpired);
    public void updateAccountNonLocked(Long id,boolean accountNonLocked);
    public void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired);
    public void updateEnabled(Long id, boolean enabled);

    public void addAuthority(Long userId, CustomerUserAuthority authority);
    public void addAuthorities(Long userId, EnumSet<CustomerUserAuthority> authorities);
    public void removeAuthority(Long userId, CustomerUserAuthority authority);
    public void removeAuthorities(Long userId, EnumSet<CustomerUserAuthority> authorities);

    public void addRole(Long userId, CustomerUserRole role);
    public void addRoles(Long userId, EnumSet<CustomerUserRole> roles);
    public void removeRole(Long userId, CustomerUserRole role);
    public void removeRoles(Long userId, EnumSet<CustomerUserRole> roles);

    public void deleteById(Long id);
}
