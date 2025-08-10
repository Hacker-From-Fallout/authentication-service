package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.SellerUserRole;
import edu.ignat.chernyshov.user.domain.entities.SellerUser;
import edu.ignat.chernyshov.user.exception.exceptions.AlreadyExistsException;
import edu.ignat.chernyshov.user.exception.exceptions.UserNotFoundException;
import edu.ignat.chernyshov.user.repositories.SellerUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerUserServiceImpl implements SellerUserService {

    private final SellerUserRepository SellerUserRepository;
    private final PasswordEncoder bCyPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<SellerUser> findAll() {
        return SellerUserRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public SellerUser findById(Long id) {
        return SellerUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public SellerUser findByEmail(String email) {
        return SellerUserRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public SellerUser findByPhoneNumber(String phoneNumber) {
        return SellerUserRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с phoneNumber: " + phoneNumber));
    }

    @Override
    @Transactional(readOnly = true)
    public SellerUser findByUsername(String username) {
        return SellerUserRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с username: " + username));
    
    }

    @Override
    @Transactional()
    public SellerUser createUser(Long organizationId, EnumSet<SellerUserRole> roles, 
                            EnumSet<SellerUserAuthority> authorities, boolean accountNonExpired,
                            boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, 
                            String firstName, String lastName, String username, String email,
                            String phoneNumber, String password) {
        existsByUsername(username);
        existsByEmail(email);
        existsByPhoneNumber(phoneNumber);

        SellerUser sellerUser = SellerUser.builder()
                .organizationId(organizationId)
                .roles(roles)
                .authorities(authorities)
                .accountNonExpired(accountNonExpired)
                .accountNonLocked(accountNonLocked)
                .credentialsNonExpired(credentialsNonExpired)
                .enabled(enabled)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .email(email)
                .phoneNumber(phoneNumber)
                .hashPassword(bCyPasswordEncoder.encode(password))
                .build();

        return SellerUserRepository.save(sellerUser);
    }

    @Override
    @Transactional()
    public SellerUser updateUser(Long id, EnumSet<SellerUserRole> roles, 
                            EnumSet<SellerUserAuthority> authorities, Boolean accountNonExpired, 
                            Boolean accountNonLocked, Boolean credentialsNonExpired, 
                            Boolean enabled, String firstName, String lastName, 
                            String username, String email, String phoneNumber, 
                            String password, LocalDateTime lastLoginDate) {
        SellerUser sellerUser = SellerUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

        if(roles != null) {
            sellerUser.setRoles(roles);
        }
        if (authorities != null) {
            sellerUser.setAuthorities(authorities);
        }
        if (accountNonExpired != null) {
            sellerUser.setAccountNonExpired(accountNonExpired.booleanValue());
        }
        if (accountNonLocked != null) {
            sellerUser.setAccountNonLocked(accountNonLocked.booleanValue());
        }
        if (credentialsNonExpired != null) {
            sellerUser.setCredentialsNonExpired(credentialsNonExpired.booleanValue());
        }
        if (enabled != null) {
            sellerUser.setEnabled(enabled.booleanValue());
        }
        if (firstName != null) {
            sellerUser.setFirstName(firstName);
        }
        if (lastName != null) {
            sellerUser.setLastName(lastName);
        }
        if (username != null) {
            existsByUsername(username);
            sellerUser.setUsername(username);;
        }
        if (email != null) {
            existsByEmail(email);
            sellerUser.setEmail(email);;
        }
        if (phoneNumber != null) {
            existsByPhoneNumber(phoneNumber);
            sellerUser.setPhoneNumber(phoneNumber);
        }
        if (password != null) {
            sellerUser.setHashPassword(bCyPasswordEncoder.encode(password));
        }
        if (lastLoginDate != null) {
            sellerUser.setLastLoginDate(lastLoginDate);
        }

        return SellerUserRepository.save(sellerUser);
    }

    @Override
    @Transactional
    public void updateFirstName(Long id, String firstName) {
        SellerUserRepository.updateFirstName(id, firstName);
    }

    @Override
    @Transactional
    public void updateLastName(Long id, String lastName) {
        SellerUserRepository.updateLastName(id, lastName);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(Long id, String phoneNumber) {
        SellerUserRepository.updatePhoneNumber(id, phoneNumber);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, String email) {
        SellerUserRepository.updateEmail(id, email);
    }

    @Override
    @Transactional
    public void updateUsername(Long id, String username) {
        SellerUserRepository.updateUsername(id, username);
    }

    @Override
    @Transactional
    public void updateHashPassword(Long id, String hashPassword) {
        SellerUserRepository.updateHashPassword(id, hashPassword);
    }

    @Override
    @Transactional
    public void updateLastLoginDate(Long id, LocalDateTime lastLoginDate) {
        SellerUserRepository.updateLastLoginDate(id, lastLoginDate);
    }

    @Override
    @Transactional
    public void updateRoles(Long id, EnumSet<SellerUserRole> roles) {
        SellerUser sellerUser = SellerUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

        sellerUser.setRoles(roles);
        SellerUserRepository.save(sellerUser);
    }

    @Override
    @Transactional
    public void updateAuthorities(Long id, EnumSet<SellerUserAuthority> authorities) {
        SellerUser sellerUser = SellerUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));
        
        sellerUser.setAuthorities(authorities);
        SellerUserRepository.save(sellerUser);
    }

    @Override
    @Transactional
    public void updateAccountNonExpired(Long id, boolean accountNonExpired) {
        SellerUserRepository.updateAccountNonExpired(id, accountNonExpired);
    }

    @Override
    @Transactional
    public void updateAccountNonLocked(Long id, boolean accountNonLocked) {
        SellerUserRepository.updateAccountNonLocked(id, accountNonLocked);
    }

    @Override
    @Transactional
    public void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired) {
        SellerUserRepository.updateCredentialsNonExpired(id, credentialsNonExpired);
    }

    @Override
    @Transactional
    public void updateEnabled(Long id, boolean enabled) {
        SellerUserRepository.updateEnabled(id, enabled);
    }

    @Override
    @Transactional
    public void addAuthority(Long userId, SellerUserAuthority authority) {
        SellerUserRepository.addAuthority(userId, authority.name());
    }

    @Override
    @Transactional
    public void addAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities) {
        for (SellerUserAuthority authority : authorities) {
            SellerUserRepository.addAuthority(userId, authority.name());
        }
    }

    @Override
    @Transactional
    public void removeAuthority(Long userId, SellerUserAuthority authority) {
        SellerUserRepository.removeAuthority(userId , authority.name());
    }

    @Override
    @Transactional
    public void removeAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities) {
        for (SellerUserAuthority authority : authorities) {
            SellerUserRepository.removeAuthority(userId, authority.name());
        }
    }

    @Override
    @Transactional
    public void addRole(Long userId, SellerUserRole role) {
        SellerUserRepository.addRole(userId, role.name());
    }
    
    @Override
    @Transactional
    public void addRoles(Long userId, EnumSet<SellerUserRole> roles) {
        for (SellerUserRole role : roles) {
            SellerUserRepository.addRole(userId, role.name());
        }
    }

    @Override
    @Transactional
    public void removeRole(Long userId, SellerUserRole role) {
        SellerUserRepository.removeRole(userId, role.name());
    }

    @Override
    @Transactional
    public void removeRoles(Long userId, EnumSet<SellerUserRole> roles) {
        for (SellerUserRole role : roles) {
            SellerUserRepository.removeRole(userId, role.name());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        SellerUserRepository.deleteById(id);
    }

    private void existsByUsername(String username) {
        if (SellerUserRepository.existsByUsername(username)) {
            throw new AlreadyExistsException("Пользователь с username " + username + "уже существует");
        }
    }

    private void existsByEmail(String email) {
        if (SellerUserRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("Пользователь с email " + email + "уже существует");
        }
    }

    private void existsByPhoneNumber(String phoneNumber) {
        if (SellerUserRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException("Пользователь с phoneNumber " + phoneNumber + "уже существует");
        }
    }
}
