package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.SellerUserRole;
import edu.ignat.chernyshov.user.domain.dto.request.SellerUserCreateDto;
import edu.ignat.chernyshov.user.domain.dto.request.SellerUserUpdateDto;
import edu.ignat.chernyshov.user.domain.entities.SellerUser;

public interface SellerUserService {
    public List<SellerUser> findAll();

    public SellerUser findById(Long id);
    public SellerUser findByEmail(String email);
    public SellerUser findByPhoneNumber(String phoneNumber);
    public SellerUser findByUsername(String username);

    public SellerUser createUser(SellerUserCreateDto dto);
    public SellerUser updateUser(Long id, SellerUserUpdateDto dto);

    public void updateFirstName(Long id, String firstName);
    public void updateLastName(Long id, String lastName);
    public void updatePhoneNumber(Long id, String phoneNumber);
    public void updateEmail(Long id, String email);
    public void updateUsername(Long id, String username);
    public void updateHashPassword(Long id, String hashPassword);
    public void updateLastLoginDate(Long id, LocalDateTime lastLoginDate);
    public void updateRoles(Long id, EnumSet<SellerUserRole> roles);
    public void updateAuthorities(Long id, EnumSet<SellerUserAuthority> authorities);
    public void updateAccountNonExpired(Long id, boolean accountNonExpired);
    public void updateAccountNonLocked(Long id,boolean accountNonLocked);
    public void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired);
    public void updateEnabled(Long id, boolean enabled);

    public void addAuthority(Long userId, SellerUserAuthority authority);
    public void addAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities);
    public void removeAuthority(Long userId, SellerUserAuthority authority);
    public void removeAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities);

    public void addRole(Long userId, SellerUserRole role);
    public void addRoles(Long userId, EnumSet<SellerUserRole> roles);
    public void removeRole(Long userId, SellerUserRole role);
    public void removeRoles(Long userId, EnumSet<SellerUserRole> roles);

    public void deleteById(Long id);
}
