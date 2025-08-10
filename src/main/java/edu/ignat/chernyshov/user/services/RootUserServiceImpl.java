package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.RootUserRole;
import edu.ignat.chernyshov.user.domain.entities.RootUser;
import edu.ignat.chernyshov.user.exception.exceptions.AlreadyExistsException;
import edu.ignat.chernyshov.user.exception.exceptions.UserNotFoundException;
import edu.ignat.chernyshov.user.repositories.RootUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RootUserServiceImpl implements RootUserService {

    private final RootUserRepository RootUserRepository;
    private final PasswordEncoder bCyPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<RootUser> findAll() {
        return RootUserRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public RootUser findById(Long id) {
        return RootUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public RootUser findByEmail(String email) {
        return RootUserRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public RootUser findByPhoneNumber(String phoneNumber) {
        return RootUserRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с phoneNumber: " + phoneNumber));
    }

    @Override
    @Transactional(readOnly = true)
    public RootUser findByUsername(String username) {
        return RootUserRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с username: " + username));
    
    }

    @Override
    @Transactional()
    public RootUser createUser(EnumSet<RootUserRole> roles, EnumSet<RootUserAuthority> authorities,
                            boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired,
                            boolean enabled, String firstName, String lastName, String username, String email,
                            String phoneNumber, String password) {
        existsByUsername(username);
        existsByEmail(email);
        existsByPhoneNumber(phoneNumber);

        RootUser rootUser = RootUser.builder()
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

        return RootUserRepository.save(rootUser);
    }

    @Override
    @Transactional()
    public RootUser updateUser(Long id, EnumSet<RootUserRole> roles, EnumSet<RootUserAuthority> authorities,
                            Boolean accountNonExpired, Boolean accountNonLocked, Boolean credentialsNonExpired,
                            Boolean enabled, String firstName, String lastName, String username, String email, 
                            String phoneNumber, String password, LocalDateTime lastLoginDate) {
        RootUser rootUser = RootUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

        if(roles != null) {
            rootUser.setRoles(roles);
        }
        if (authorities != null) {
            rootUser.setAuthorities(authorities);
        }
        if (accountNonExpired != null) {
            rootUser.setAccountNonExpired(accountNonExpired.booleanValue());
        }
        if (accountNonLocked != null) {
            rootUser.setAccountNonLocked(accountNonLocked.booleanValue());
        }
        if (credentialsNonExpired != null) {
            rootUser.setCredentialsNonExpired(credentialsNonExpired.booleanValue());
        }
        if (enabled != null) {
            rootUser.setEnabled(enabled.booleanValue());
        }
        if (firstName != null) {
            rootUser.setFirstName(firstName);
        }
        if (lastName != null) {
            rootUser.setLastName(lastName);
        }
        if (username != null) {
            existsByUsername(username);
            rootUser.setUsername(username);;
        }
        if (email != null) {
            existsByEmail(email);
            rootUser.setEmail(email);;
        }
        if (phoneNumber != null) {
            existsByPhoneNumber(phoneNumber);
            rootUser.setPhoneNumber(phoneNumber);
        }
        if (password != null) {
            rootUser.setHashPassword(bCyPasswordEncoder.encode(password));
        }
        if (lastLoginDate != null) {
            rootUser.setLastLoginDate(lastLoginDate);
        }

        return RootUserRepository.save(rootUser);
    }

    @Override
    @Transactional
    public void updateFirstName(Long id, String firstName) {
        RootUserRepository.updateFirstName(id, firstName);
    }

    @Override
    @Transactional
    public void updateLastName(Long id, String lastName) {
        RootUserRepository.updateLastName(id, lastName);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(Long id, String phoneNumber) {
        RootUserRepository.updatePhoneNumber(id, phoneNumber);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, String email) {
        RootUserRepository.updateEmail(id, email);
    }

    @Override
    @Transactional
    public void updateUsername(Long id, String username) {
        RootUserRepository.updateUsername(id, username);
    }

    @Override
    @Transactional
    public void updateHashPassword(Long id, String hashPassword) {
        RootUserRepository.updateHashPassword(id, hashPassword);
    }

    @Override
    @Transactional
    public void updateLastLoginDate(Long id, LocalDateTime lastLoginDate) {
        RootUserRepository.updateLastLoginDate(id, lastLoginDate);
    }

    @Override
    @Transactional
    public void updateRoles(Long id, EnumSet<RootUserRole> roles) {
        RootUser rootUser = RootUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

        rootUser.setRoles(roles);
        RootUserRepository.save(rootUser);
    }

    @Override
    @Transactional
    public void updateAuthorities(Long id, EnumSet<RootUserAuthority> authorities) {
        RootUser rootUser = RootUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));
        
        rootUser.setAuthorities(authorities);
        RootUserRepository.save(rootUser);
    }

    @Override
    @Transactional
    public void updateAccountNonExpired(Long id, boolean accountNonExpired) {
        RootUserRepository.updateAccountNonExpired(id, accountNonExpired);
    }

    @Override
    @Transactional
    public void updateAccountNonLocked(Long id, boolean accountNonLocked) {
        RootUserRepository.updateAccountNonLocked(id, accountNonLocked);
    }

    @Override
    @Transactional
    public void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired) {
        RootUserRepository.updateCredentialsNonExpired(id, credentialsNonExpired);
    }

    @Override
    @Transactional
    public void updateEnabled(Long id, boolean enabled) {
        RootUserRepository.updateEnabled(id, enabled);
    }

    @Override
    @Transactional
    public void addAuthority(Long userId, RootUserAuthority authority) {
        RootUserRepository.addAuthority(userId, authority.name());
    }

    @Override
    @Transactional
    public void addAuthorities(Long userId, EnumSet<RootUserAuthority> authorities) {
        for (RootUserAuthority authority : authorities) {
            RootUserRepository.addAuthority(userId, authority.name());
        }
    }

    @Override
    @Transactional
    public void removeAuthority(Long userId, RootUserAuthority authority) {
        RootUserRepository.removeAuthority(userId , authority.name());
    }

    @Override
    @Transactional
    public void removeAuthorities(Long userId, EnumSet<RootUserAuthority> authorities) {
        for (RootUserAuthority authority : authorities) {
            RootUserRepository.removeAuthority(userId, authority.name());
        }
    }

    @Override
    @Transactional
    public void addRole(Long userId, RootUserRole role) {
        RootUserRepository.addRole(userId, role.name());
    }
    
    @Override
    @Transactional
    public void addRoles(Long userId, EnumSet<RootUserRole> roles) {
        for (RootUserRole role : roles) {
            RootUserRepository.addRole(userId, role.name());
        }
    }

    @Override
    @Transactional
    public void removeRole(Long userId, RootUserRole role) {
        RootUserRepository.removeRole(userId, role.name());
    }

    @Override
    @Transactional
    public void removeRoles(Long userId, EnumSet<RootUserRole> roles) {
        for (RootUserRole role : roles) {
            RootUserRepository.removeRole(userId, role.name());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        RootUserRepository.deleteById(id);
    }

    private void existsByUsername(String username) {
        if (RootUserRepository.existsByUsername(username)) {
            throw new AlreadyExistsException("Пользователь с username " + username + "уже существует");
        }
    }

    private void existsByEmail(String email) {
        if (RootUserRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("Пользователь с email " + email + "уже существует");
        }
    }

    private void existsByPhoneNumber(String phoneNumber) {
        if (RootUserRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException("Пользователь с phoneNumber " + phoneNumber + "уже существует");
        }
    }
}
