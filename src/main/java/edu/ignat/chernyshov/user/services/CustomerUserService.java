package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.CustomerUserRole;
import edu.ignat.chernyshov.user.domain.entities.CustomerUser;

public interface CustomerUserService {
    List<CustomerUser> findAll();

    CustomerUser findById(Long id);
    CustomerUser findByEmail(String email);
    CustomerUser findByPhoneNumber(String phoneNumber);
    CustomerUser findByUsername(String username);

    CustomerUser createUser(Set<CustomerUserRole> roles, Set<CustomerUserAuthority> authorities, 
                            String firstName, String lastName, String username, String email,
                            String phoneNumber, String password);

    CustomerUser updateUser(Long id, Set<CustomerUserRole> roles, Set<CustomerUserAuthority> authorities, 
                            String firstName, String lastName, String username, String email, 
                            String phoneNumber, String password, LocalDateTime lastLoginDate);

    void updateFirstName(Long id, String firstName);
    void updateLastName(Long id, String lastName);
    void updatePhoneNumber(Long id, String phoneNumber);
    void updateEmail(Long id, String email);
    void updateUsername(Long id, String username);
    void updateHashPassword(Long id, String hashPassword);
    void updateLastLoginDate(Long id, LocalDateTime lastLoginDate);
    void updateRoles(Long id, Set<CustomerUserRole> roles);
    void updateAuthorities(Long id, Set<CustomerUserAuthority> authorities);

    void addAuthority(Long userId, String authority);
    void addAuthorities(Long userId, Set<CustomerUserAuthority> authorities);
    void removeAuthority(Long userId, String authority);
    void removeAuthorities(Long userId, Set<CustomerUserAuthority> authorities);

    void addRole(Long userId, String role);
    void addRoles(Long userId, Set<CustomerUserRole> roles);
    void removeRole(Long userId, String role);
    void removeRoles(Long userId, Set<CustomerUserRole> roles);

    void deleteById(Long id);
}
