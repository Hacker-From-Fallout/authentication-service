package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.SellerUserRole;
import edu.ignat.chernyshov.user.domain.dto.request.SellerUserCreateDto;
import edu.ignat.chernyshov.user.domain.dto.request.SellerUserUpdateDto;
import edu.ignat.chernyshov.user.domain.entities.SellerUser;
import edu.ignat.chernyshov.user.exception.exceptions.AlreadyExistsException;
import edu.ignat.chernyshov.user.exception.exceptions.UserNotFoundException;
import edu.ignat.chernyshov.user.repositories.SellerUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultSellerUserService implements SellerUserService {

    private final SellerUserRepository sellerUserRepository;
    private final PasswordEncoder bCyPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<SellerUser> findAll() {
        return sellerUserRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public SellerUser findById(Long id) {
        return sellerUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public SellerUser findByEmail(String email) {
        return sellerUserRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public SellerUser findByPhoneNumber(String phoneNumber) {
        return sellerUserRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с phoneNumber: " + phoneNumber));
    }

    @Override
    @Transactional(readOnly = true)
    public SellerUser findByUsername(String username) {
        return sellerUserRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с username: " + username));
    
    }

    @Override
    @Transactional
    public SellerUser createUser(SellerUserCreateDto dto) {
        existsByUsername(dto.username());
        existsByEmail(dto.email());
        existsByPhoneNumber(dto.phoneNumber());

        SellerUser sellerUser = SellerUser.builder()
                .organizationId(dto.organizationId())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .username(dto.username())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .hashPassword(bCyPasswordEncoder.encode(dto.password()))
                .roles(dto.roles())
                .authorities(dto.authorities())
                .accountNonExpired(dto.accountNonExpired())
                .accountNonLocked(dto.accountNonLocked())
                .credentialsNonExpired(dto.credentialsNonExpired())
                .enabled(dto.enabled())
                .build();

        return sellerUserRepository.save(sellerUser);
    }

    @Override
    @Transactional
    public SellerUser updateUser(Long id, SellerUserUpdateDto dto) {
        SellerUser sellerUser = sellerUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

            if (dto.firstName() != null) {
                sellerUser.setFirstName(dto.firstName());
            }
            if (dto.lastName() != null) {
                sellerUser.setLastName(dto.lastName());
            }
            if (dto.username() != null) {
                existsByUsername(dto.username());
                sellerUser.setUsername(dto.username());
            }
            if (dto.email() != null) {
                existsByEmail(dto.email());
                sellerUser.setEmail(dto.email());
            }
            if (dto.phoneNumber() != null) {
                existsByPhoneNumber(dto.phoneNumber());
                sellerUser.setPhoneNumber(dto.phoneNumber());
            }
            if (dto.password() != null) {
                sellerUser.setHashPassword(bCyPasswordEncoder.encode(dto.password()));
            }
            if (dto.roles() != null) {
                sellerUser.setRoles(dto.roles());
            }
            if (dto.authorities() != null) {
                sellerUser.setAuthorities(dto.authorities());
            }
            if (dto.accountNonExpired() != null) {
                sellerUser.setAccountNonExpired(dto.accountNonExpired());
            }
            if (dto.accountNonLocked() != null) {
                sellerUser.setAccountNonLocked(dto.accountNonLocked());
            }
            if (dto.credentialsNonExpired() != null) {
                sellerUser.setCredentialsNonExpired(dto.credentialsNonExpired());
            }
            if (dto.enabled() != null) {
                sellerUser.setEnabled(dto.enabled());
            }
            if (dto.lastLoginDate() != null) {
                sellerUser.setLastLoginDate(dto.lastLoginDate());
            }

            return sellerUserRepository.save(sellerUser);
    }

    @Override
    @Transactional
    public void updateFirstName(Long id, String firstName) {
        ensureUserExists(id);
        sellerUserRepository.updateFirstName(id, firstName);
    }

    @Override
    @Transactional
    public void updateLastName(Long id, String lastName) {
        ensureUserExists(id);
        sellerUserRepository.updateLastName(id, lastName);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(Long id, String phoneNumber) {
        ensureUserExists(id);
        sellerUserRepository.updatePhoneNumber(id, phoneNumber);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, String email) {
        ensureUserExists(id);
        sellerUserRepository.updateEmail(id, email);
    }

    @Override
    @Transactional
    public void updateUsername(Long id, String username) {
        ensureUserExists(id);
        sellerUserRepository.updateUsername(id, username);
    }

    @Override
    @Transactional
    public void updateHashPassword(Long id, String hashPassword) {
        ensureUserExists(id);
        sellerUserRepository.updateHashPassword(id, hashPassword);
    }

    @Override
    @Transactional
    public void updateLastLoginDate(Long id, LocalDateTime lastLoginDate) {
        ensureUserExists(id);
        sellerUserRepository.updateLastLoginDate(id, lastLoginDate);
    }

    @Override
    @Transactional
    public void updateRoles(Long id, EnumSet<SellerUserRole> roles) {
        SellerUser sellerUser = sellerUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

        sellerUser.setRoles(roles);
        sellerUserRepository.save(sellerUser);
    }

    @Override
    @Transactional
    public void updateAuthorities(Long id, EnumSet<SellerUserAuthority> authorities) {
        SellerUser sellerUser = sellerUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));
        
        sellerUser.setAuthorities(authorities);
        sellerUserRepository.save(sellerUser);
    }

    @Override
    @Transactional
    public void updateAccountNonExpired(Long id, boolean accountNonExpired) {
        ensureUserExists(id);
        sellerUserRepository.updateAccountNonExpired(id, accountNonExpired);
    }

    @Override
    @Transactional
    public void updateAccountNonLocked(Long id, boolean accountNonLocked) {
        ensureUserExists(id);
        sellerUserRepository.updateAccountNonLocked(id, accountNonLocked);
    }

    @Override
    @Transactional
    public void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired) {
        ensureUserExists(id);
        sellerUserRepository.updateCredentialsNonExpired(id, credentialsNonExpired);
    }

    @Override
    @Transactional
    public void updateEnabled(Long id, boolean enabled) {
        ensureUserExists(id);
        sellerUserRepository.updateEnabled(id, enabled);
    }

    @Override
    @Transactional
    public void addAuthority(Long userId, SellerUserAuthority authority) {
        ensureUserExists(userId);
        sellerUserRepository.addAuthority(userId, authority.name());
    }

    @Override
    @Transactional
    public void addAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities) {
        ensureUserExists(userId);
        for (SellerUserAuthority authority : authorities) {
            sellerUserRepository.addAuthority(userId, authority.name());
        }
    }

    @Override
    @Transactional
    public void removeAuthority(Long userId, SellerUserAuthority authority) {
        ensureUserExists(userId);
        sellerUserRepository.removeAuthority(userId , authority.name());
    }

    @Override
    @Transactional
    public void removeAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities) {
        ensureUserExists(userId);
        for (SellerUserAuthority authority : authorities) {
            sellerUserRepository.removeAuthority(userId, authority.name());
        }
    }

    @Override
    @Transactional
    public void addRole(Long userId, SellerUserRole role) {
        ensureUserExists(userId);
        sellerUserRepository.addRole(userId, role.name());
    }
    
    @Override
    @Transactional
    public void addRoles(Long userId, EnumSet<SellerUserRole> roles) {
        ensureUserExists(userId);
        for (SellerUserRole role : roles) {
            sellerUserRepository.addRole(userId, role.name());
        }
    }

    @Override
    @Transactional
    public void removeRole(Long userId, SellerUserRole role) {
        ensureUserExists(userId);
        sellerUserRepository.removeRole(userId, role.name());
    }

    @Override
    @Transactional
    public void removeRoles(Long userId, EnumSet<SellerUserRole> roles) {
        ensureUserExists(userId);
        for (SellerUserRole role : roles) {
            sellerUserRepository.removeRole(userId, role.name());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        sellerUserRepository.deleteById(id);
    }

    private void ensureUserExists(Long id) {
        if (!sellerUserRepository.existsById(id)) {
            throw new UserNotFoundException("Пользователь не найден с id: " + id);
        }
    }

    private void existsByUsername(String username) {
        if (sellerUserRepository.existsByUsername(username)) {
            throw new AlreadyExistsException("Пользователь с username " + username + " уже существует");
        }
    }

    private void existsByEmail(String email) {
        if (sellerUserRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("Пользователь с email " + email + " уже существует");
        }
    }

    private void existsByPhoneNumber(String phoneNumber) {
        if (sellerUserRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException("Пользователь с phoneNumber " + phoneNumber + " уже существует");
        }
    }
}
