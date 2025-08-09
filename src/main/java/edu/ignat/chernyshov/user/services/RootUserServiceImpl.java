package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.RootUserRole;
import edu.ignat.chernyshov.user.domain.entities.RootUser;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RootUserServiceImpl implements RootUserService {

    @Override
    public List<RootUser> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public RootUser findById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public RootUser findByEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public RootUser findByPhoneNumber(String phoneNumber) {
        throw new UnsupportedOperationException("Unimplemented method 'findByPhoneNumber'");
    }

    @Override
    public RootUser findByUsername(String username) {
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    @Override
    public RootUser createUser(Set<RootUserRole> roles, Set<RootUserAuthority> authorities, String firstName,
            String lastName, String username, String email, String phoneNumber, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public RootUser updateUser(Long id, Set<RootUserRole> roles, Set<RootUserAuthority> authorities, String firstName,
            String lastName, String username, String email, String phoneNumber, String password,
            LocalDateTime lastLoginDate) {
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void updateFirstName(Long id, String firstName) {
        throw new UnsupportedOperationException("Unimplemented method 'updateFirstName'");
    }

    @Override
    public void updateLastName(Long id, String lastName) {
        throw new UnsupportedOperationException("Unimplemented method 'updateLastName'");
    }

    @Override
    public void updatePhoneNumber(Long id, String phoneNumber) {
        throw new UnsupportedOperationException("Unimplemented method 'updatePhoneNumber'");
    }

    @Override
    public void updateEmail(Long id, String email) {
        throw new UnsupportedOperationException("Unimplemented method 'updateEmail'");
    }

    @Override
    public void updateUsername(Long id, String username) {
        throw new UnsupportedOperationException("Unimplemented method 'updateUsername'");
    }

    @Override
    public void updateHashPassword(Long id, String hashPassword) {
        throw new UnsupportedOperationException("Unimplemented method 'updateHashPassword'");
    }

    @Override
    public void updateLastLoginDate(Long id, LocalDateTime lastLoginDate) {
        throw new UnsupportedOperationException("Unimplemented method 'updateLastLoginDate'");
    }

    @Override
    public void updateRoles(Long id, Set<RootUserRole> roles) {
        throw new UnsupportedOperationException("Unimplemented method 'updateRoles'");
    }

    @Override
    public void updateAuthorities(Long id, Set<RootUserAuthority> authorities) {
        throw new UnsupportedOperationException("Unimplemented method 'updateAuthorities'");
    }

    @Override
    public void addAuthority(Long userId, String authority) {
        throw new UnsupportedOperationException("Unimplemented method 'addAuthority'");
    }

    @Override
    public void addAuthorities(Long userId, Set<RootUserAuthority> authorities) {
        throw new UnsupportedOperationException("Unimplemented method 'addAuthorities'");
    }

    @Override
    public void removeAuthority(Long userId, String authority) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAuthority'");
    }

    @Override
    public void removeAuthorities(Long userId, Set<RootUserAuthority> authorities) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAuthorities'");
    }

    @Override
    public void addRole(Long userId, String role) {
        throw new UnsupportedOperationException("Unimplemented method 'addRole'");
    }

    @Override
    public void addRoles(Long userId, Set<RootUserRole> roles) {
        throw new UnsupportedOperationException("Unimplemented method 'addRoles'");
    }

    @Override
    public void removeRole(Long userId, String role) {
        throw new UnsupportedOperationException("Unimplemented method 'removeRole'");
    }

    @Override
    public void removeRoles(Long userId, Set<RootUserRole> roles) {
        throw new UnsupportedOperationException("Unimplemented method 'removeRoles'");
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
