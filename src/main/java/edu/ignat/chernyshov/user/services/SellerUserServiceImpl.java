package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.SellerUserRole;
import edu.ignat.chernyshov.user.domain.entities.SellerUser;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerUserServiceImpl implements SellerUserService {

    @Override
    public List<SellerUser> findAll() {
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public SellerUser findById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public SellerUser findByEmail(String email) {
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }

    @Override
    public SellerUser findByPhoneNumber(String phoneNumber) {
        throw new UnsupportedOperationException("Unimplemented method 'findByPhoneNumber'");
    }

    @Override
    public SellerUser findByUsername(String username) {
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }

    @Override
    public SellerUser createUser(Set<SellerUserRole> roles, Set<SellerUserAuthority> authorities, String firstName,
            String lastName, String username, String email, String phoneNumber, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public SellerUser updateUser(Long id, Set<SellerUserRole> roles, Set<SellerUserAuthority> authorities,
            String firstName, String lastName, String username, String email, String phoneNumber, String password,
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
    public void updateRoles(Long id, Set<SellerUserRole> roles) {
        throw new UnsupportedOperationException("Unimplemented method 'updateRoles'");
    }

    @Override
    public void updateAuthorities(Long id, Set<SellerUserAuthority> authorities) {
        throw new UnsupportedOperationException("Unimplemented method 'updateAuthorities'");
    }

    @Override
    public void addAuthority(Long userId, String authority) {
        throw new UnsupportedOperationException("Unimplemented method 'addAuthority'");
    }

    @Override
    public void addAuthorities(Long userId, Set<SellerUserAuthority> authorities) {
        throw new UnsupportedOperationException("Unimplemented method 'addAuthorities'");
    }

    @Override
    public void removeAuthority(Long userId, String authority) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAuthority'");
    }

    @Override
    public void removeAuthorities(Long userId, Set<SellerUserAuthority> authorities) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAuthorities'");
    }

    @Override
    public void addRole(Long userId, String role) {
        throw new UnsupportedOperationException("Unimplemented method 'addRole'");
    }

    @Override
    public void addRoles(Long userId, Set<SellerUserRole> roles) {
        throw new UnsupportedOperationException("Unimplemented method 'addRoles'");
    }

    @Override
    public void removeRole(Long userId, String role) {
        throw new UnsupportedOperationException("Unimplemented method 'removeRole'");
    }

    @Override
    public void removeRoles(Long userId, Set<SellerUserRole> roles) {
        throw new UnsupportedOperationException("Unimplemented method 'removeRoles'");
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
}
