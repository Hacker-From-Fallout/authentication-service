package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.SellerUserRole;
import edu.ignat.chernyshov.user.domain.entities.SellerUser;

public interface SellerUserService {
    List<SellerUser> findAll();

    SellerUser findById(Long id);
    SellerUser findByEmail(String email);
    SellerUser findByPhoneNumber(String phoneNumber);
    SellerUser findByUsername(String username);

    SellerUser createUser(Set<SellerUserRole> roles, Set<SellerUserAuthority> authorities, 
                            String firstName, String lastName, String username, String email,
                            String phoneNumber, String password);

    SellerUser updateUser(Long id, Set<SellerUserRole> roles, Set<SellerUserAuthority> authorities, 
                            String firstName, String lastName, String username, String email, 
                            String phoneNumber, String password, LocalDateTime lastLoginDate);

    void updateFirstName(Long id, String firstName);
    void updateLastName(Long id, String lastName);
    void updatePhoneNumber(Long id, String phoneNumber);
    void updateEmail(Long id, String email);
    void updateUsername(Long id, String username);
    void updateHashPassword(Long id, String hashPassword);
    void updateLastLoginDate(Long id, LocalDateTime lastLoginDate);
    void updateRoles(Long id, Set<SellerUserRole> roles);
    void updateAuthorities(Long id, Set<SellerUserAuthority> authorities);

    void addAuthority(Long userId, String authority);
    void addAuthorities(Long userId, Set<SellerUserAuthority> authorities);
    void removeAuthority(Long userId, String authority);
    void removeAuthorities(Long userId, Set<SellerUserAuthority> authorities);

    void addRole(Long userId, String role);
    void addRoles(Long userId, Set<SellerUserRole> roles);
    void removeRole(Long userId, String role);
    void removeRoles(Long userId, Set<SellerUserRole> roles);

    void deleteById(Long id);
}
