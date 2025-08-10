package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.SellerUserRole;
import edu.ignat.chernyshov.user.domain.entities.SellerUser;

public interface SellerUserService {
    List<SellerUser> findAll();

    SellerUser findById(Long id);
    SellerUser findByEmail(String email);
    SellerUser findByPhoneNumber(String phoneNumber);
    SellerUser findByUsername(String username);

    SellerUser createUser(Long organizationId, EnumSet<SellerUserRole> roles, 
                            EnumSet<SellerUserAuthority> authorities, boolean accountNonExpired, 
                            boolean accountNonLocked, boolean credentialsNonExpired,
                            boolean enabled, String firstName, String lastName, String username, 
                            String email, String phoneNumber, String password);

    SellerUser updateUser(Long id, EnumSet<SellerUserRole> roles, EnumSet<SellerUserAuthority> authorities,
                            Boolean accountNonExpired, Boolean accountNonLocked, 
                            Boolean credentialsNonExpired, Boolean enabled, String firstName, 
                            String lastName, String username, String email, String phoneNumber, 
                            String password, LocalDateTime lastLoginDate);

    void updateFirstName(Long id, String firstName);
    void updateLastName(Long id, String lastName);
    void updatePhoneNumber(Long id, String phoneNumber);
    void updateEmail(Long id, String email);
    void updateUsername(Long id, String username);
    void updateHashPassword(Long id, String hashPassword);
    void updateLastLoginDate(Long id, LocalDateTime lastLoginDate);
    void updateRoles(Long id, EnumSet<SellerUserRole> roles);
    void updateAuthorities(Long id, EnumSet<SellerUserAuthority> authorities);
    void updateAccountNonExpired(Long id, boolean accountNonExpired);
    void updateAccountNonLocked(Long id,boolean accountNonLocked);
    void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired);
    void updateEnabled(Long id, boolean enabled);

    void addAuthority(Long userId, SellerUserAuthority authority);
    void addAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities);
    void removeAuthority(Long userId, SellerUserAuthority authority);
    void removeAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities);

    void addRole(Long userId, SellerUserRole role);
    void addRoles(Long userId, EnumSet<SellerUserRole> roles);
    void removeRole(Long userId, SellerUserRole role);
    void removeRoles(Long userId, EnumSet<SellerUserRole> roles);

    void deleteById(Long id);
}
