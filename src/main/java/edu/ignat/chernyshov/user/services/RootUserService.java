package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.RootUserRole;
import edu.ignat.chernyshov.user.domain.entities.RootUser;

public interface RootUserService {
    List<RootUser> findAll();

    RootUser findById(Long id);
    RootUser findByEmail(String email);
    RootUser findByPhoneNumber(String phoneNumber);
    RootUser findByUsername(String username);

    RootUser createUser(EnumSet<RootUserRole> roles, EnumSet<RootUserAuthority> authorities,
                            boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired,
                            boolean enabled, String firstName, String lastName, String username, String email,
                            String phoneNumber, String password);

    RootUser updateUser(Long id, EnumSet<RootUserRole> roles, EnumSet<RootUserAuthority> authorities,
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
    void updateRoles(Long id, EnumSet<RootUserRole> roles);
    void updateAuthorities(Long id, EnumSet<RootUserAuthority> authorities);
    void updateAccountNonExpired(Long id, boolean accountNonExpired);
    void updateAccountNonLocked(Long id,boolean accountNonLocked);
    void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired);
    void updateEnabled(Long id, boolean enabled);

    void addAuthority(Long userId, RootUserAuthority authority);
    void addAuthorities(Long userId, EnumSet<RootUserAuthority> authorities);
    void removeAuthority(Long userId, RootUserAuthority authority);
    void removeAuthorities(Long userId, EnumSet<RootUserAuthority> authorities);

    void addRole(Long userId, RootUserRole role);
    void addRoles(Long userId, EnumSet<RootUserRole> roles);
    void removeRole(Long userId, RootUserRole role);
    void removeRoles(Long userId, EnumSet<RootUserRole> roles);

    void deleteById(Long id);
}
