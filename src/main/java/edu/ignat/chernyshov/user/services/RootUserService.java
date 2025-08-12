package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.RootUserRole;
import edu.ignat.chernyshov.user.domain.dto.request.RootUserCreateDto;
import edu.ignat.chernyshov.user.domain.dto.request.RootUserUpdateDto;
import edu.ignat.chernyshov.user.domain.entities.RootUser;

public interface RootUserService {
    public List<RootUser> findAll();

    public RootUser findById(Long id);
    public RootUser findByEmail(String email);
    public RootUser findByPhoneNumber(String phoneNumber);
    public RootUser findByUsername(String username);

    public RootUser createUser(RootUserCreateDto dto);
    public RootUser updateUser(Long id, RootUserUpdateDto dto);

    public void updateFirstName(Long id, String firstName);
    public void updateLastName(Long id, String lastName);
    public void updatePhoneNumber(Long id, String phoneNumber);
    public void updateEmail(Long id, String email);
    public void updateUsername(Long id, String username);
    public void updateHashPassword(Long id, String hashPassword);
    public void updateLastLoginDate(Long id, LocalDateTime lastLoginDate);
    public void updateRoles(Long id, EnumSet<RootUserRole> roles);
    public void updateAuthorities(Long id, EnumSet<RootUserAuthority> authorities);
    public void updateAccountNonExpired(Long id, boolean accountNonExpired);
    public void updateAccountNonLocked(Long id,boolean accountNonLocked);
    public void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired);
    public void updateEnabled(Long id, boolean enabled);

    public void addAuthority(Long userId, RootUserAuthority authority);
    public void addAuthorities(Long userId, EnumSet<RootUserAuthority> authorities);
    public void removeAuthority(Long userId, RootUserAuthority authority);
    public void removeAuthorities(Long userId, EnumSet<RootUserAuthority> authorities);

    public void addRole(Long userId, RootUserRole role);
    public void addRoles(Long userId, EnumSet<RootUserRole> roles);
    public void removeRole(Long userId, RootUserRole role);
    public void removeRoles(Long userId, EnumSet<RootUserRole> roles);

    public void deleteById(Long id);
}
