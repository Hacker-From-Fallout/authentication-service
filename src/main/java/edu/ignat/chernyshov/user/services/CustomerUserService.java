package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.CustomerUserRole;
import edu.ignat.chernyshov.user.domain.entities.CustomerUser;

public interface CustomerUserService {
    List<CustomerUser> findAll();

    CustomerUser findById(Long id);
    CustomerUser findByEmail(String email);
    CustomerUser findByPhoneNumber(String phoneNumber);
    CustomerUser findByUsername(String username);

    CustomerUser createUser(EnumSet<CustomerUserRole> roles, EnumSet<CustomerUserAuthority> authorities,
                            boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired,
                            boolean enabled, String firstName, String lastName, String username, String email,
                            String phoneNumber, String password);

    CustomerUser updateUser(Long id, EnumSet<CustomerUserRole> roles, EnumSet<CustomerUserAuthority> authorities,
                            Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired,
                            Boolean enabled, String firstName, String lastName, String username, String email, 
                            String phoneNumber, String password, LocalDateTime lastLoginDate);

    void updateFirstName(Long id, String firstName);
    void updateLastName(Long id, String lastName);
    void updatePhoneNumber(Long id, String phoneNumber);
    void updateEmail(Long id, String email);
    void updateUsername(Long id, String username);
    void updateHashPassword(Long id, String hashPassword);
    void updateLastLoginDate(Long id, LocalDateTime lastLoginDate);
    void updateRoles(Long id, EnumSet<CustomerUserRole> roles);
    void updateAuthorities(Long id, EnumSet<CustomerUserAuthority> authorities);
    void updateAccountNonExpired(Long id, boolean accountNonExpired);
    void updateAccountNonLocked(Long id,boolean accountNonLocked);
    void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired);
    void updateEnabled(Long id, boolean enabled);

    void addAuthority(Long userId, CustomerUserAuthority authority);
    void addAuthorities(Long userId, EnumSet<CustomerUserAuthority> authorities);
    void removeAuthority(Long userId, CustomerUserAuthority authority);
    void removeAuthorities(Long userId, EnumSet<CustomerUserAuthority> authorities);

    void addRole(Long userId, CustomerUserRole role);
    void addRoles(Long userId, EnumSet<CustomerUserRole> roles);
    void removeRole(Long userId, CustomerUserRole role);
    void removeRoles(Long userId, EnumSet<CustomerUserRole> roles);

    void deleteById(Long id);
}
