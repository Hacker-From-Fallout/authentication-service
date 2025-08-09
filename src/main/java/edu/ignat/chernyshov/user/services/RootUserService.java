package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.RootUserRole;
import edu.ignat.chernyshov.user.domain.entities.RootUser;

public interface RootUserService {
    List<RootUser> findAll();

    RootUser findById(Long id);
    RootUser findByEmail(String email);
    RootUser findByPhoneNumber(String phoneNumber);
    RootUser findByUsername(String username);

    RootUser createUser(Set<RootUserRole> roles, Set<RootUserAuthority> authorities, 
                            String firstName, String lastName, String username, String email,
                            String phoneNumber, String password);

    RootUser updateUser(Long id, Set<RootUserRole> roles, Set<RootUserAuthority> authorities, 
                            String firstName, String lastName, String username, String email, 
                            String phoneNumber, String password, LocalDateTime lastLoginDate);

    void updateFirstName(Long id, String firstName);
    void updateLastName(Long id, String lastName);
    void updatePhoneNumber(Long id, String phoneNumber);
    void updateEmail(Long id, String email);
    void updateUsername(Long id, String username);
    void updateHashPassword(Long id, String hashPassword);
    void updateLastLoginDate(Long id, LocalDateTime lastLoginDate);
    void updateRoles(Long id, Set<RootUserRole> roles);
    void updateAuthorities(Long id, Set<RootUserAuthority> authorities);

    void addAuthority(Long userId, String authority);
    void addAuthorities(Long userId, Set<RootUserAuthority> authorities);
    void removeAuthority(Long userId, String authority);
    void removeAuthorities(Long userId, Set<RootUserAuthority> authorities);

    void addRole(Long userId, String role);
    void addRoles(Long userId, Set<RootUserRole> roles);
    void removeRole(Long userId, String role);
    void removeRoles(Long userId, Set<RootUserRole> roles);

    void deleteById(Long id);
}
