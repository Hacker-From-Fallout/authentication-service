package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.RootUserRole;
import edu.ignat.chernyshov.user.domain.dto.request.RootUserCreateDto;
import edu.ignat.chernyshov.user.domain.dto.request.RootUserFilterDto;
import edu.ignat.chernyshov.user.domain.dto.request.RootUserUpdateDto;
import edu.ignat.chernyshov.user.domain.entities.RootUser;
import edu.ignat.chernyshov.user.exception.exceptions.AlreadyExistsException;
import edu.ignat.chernyshov.user.exception.exceptions.UserNotFoundException;
import edu.ignat.chernyshov.user.repositories.RootUserRepository;
import edu.ignat.chernyshov.user.repositories.specifications.RootUserSpecifications;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultRootUserService implements RootUserService {

    private final RootUserRepository rootUserRepository;
    private final PasswordEncoder bCyPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Page<RootUser> findByFilters(RootUserFilterDto filters, int page, int size) {
        Specification<RootUser> specification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (filters.firstName() != null && !filters.firstName().isEmpty()) {
            specification = specification.and(RootUserSpecifications.hasFirstName(filters.firstName()));
        }
        if (filters.lastName() != null && !filters.lastName().isEmpty()) {
            specification = specification.and(RootUserSpecifications.hasLastName(filters.lastName()));
        }
        if (filters.username() != null && !filters.username().isEmpty()) {
            specification = specification.and(RootUserSpecifications.hasUsername(filters.username()));
        }
        if (filters.email() != null && !filters.email().isEmpty()) {
            specification = specification.and(RootUserSpecifications.hasEmail(filters.email()));
        }
        if (filters.phoneNumber() != null && !filters.phoneNumber().isEmpty()) {
            specification = specification.and(RootUserSpecifications.hasPhoneNumber(filters.phoneNumber()));
        }

        Pageable pageable = PageRequest.of(page, size);
        return rootUserRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public RootUser findById(Long id) {
        return rootUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public RootUser findByUsername(String username) {
        return rootUserRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с username: " + username));
    
    }

    @Override
    @Transactional(readOnly = true)
    public RootUser findByEmail(String email) {
        return rootUserRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public RootUser findByPhoneNumber(String phoneNumber) {
        return rootUserRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с phoneNumber: " + phoneNumber));
    }

    @Override
    @Transactional
    public RootUser createUser(RootUserCreateDto dto) {
        existsByUsername(dto.username());
        existsByEmail(dto.email());
        existsByPhoneNumber(dto.phoneNumber());

        RootUser rootUser = RootUser.builder()
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

        return rootUserRepository.save(rootUser);
    }

    @Override
    @Transactional
    public RootUser updateUser(Long id, RootUserUpdateDto dto) {
        RootUser rootUser = rootUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

        if (dto.firstName() != null) {
            rootUser.setFirstName(dto.firstName());
        }
        if (dto.lastName() != null) {
            rootUser.setLastName(dto.lastName());
        }
        if (dto.username() != null) {
            existsByUsername(dto.username());
            rootUser.setUsername(dto.username());
        }
        if (dto.email() != null) {
            existsByEmail(dto.email());
            rootUser.setEmail(dto.email());
        }
        if (dto.phoneNumber() != null) {
            existsByPhoneNumber(dto.phoneNumber());
            rootUser.setPhoneNumber(dto.phoneNumber());
        }
        if (dto.password() != null) {
            rootUser.setHashPassword(bCyPasswordEncoder.encode(dto.password()));
        }
        if (dto.roles() != null) {
            rootUser.setRoles(dto.roles());
        }
        if (dto.authorities() != null) {
            rootUser.setAuthorities(dto.authorities());
        }
        if (dto.accountNonExpired() != null) {
                rootUser.setAccountNonExpired(dto.accountNonExpired());
        }
        if (dto.accountNonLocked() != null) {
                rootUser.setAccountNonLocked(dto.accountNonLocked());
        }
        if (dto.credentialsNonExpired() != null) {
                rootUser.setCredentialsNonExpired(dto.credentialsNonExpired());
        }
        if (dto.enabled() != null) {
                rootUser.setEnabled(dto.enabled());
        }
        if (dto.lastLoginDate() != null) {
                rootUser.setLastLoginDate(dto.lastLoginDate());
        }

        return rootUserRepository.save(rootUser);
    }

    @Override
    @Transactional
    public void updateFirstName(Long id, String firstName) {
        ensureUserExists(id);
        rootUserRepository.updateFirstName(id, firstName);
    }

    @Override
    @Transactional
    public void updateLastName(Long id, String lastName) {
        ensureUserExists(id);
        rootUserRepository.updateLastName(id, lastName);
    }

    @Override
    @Transactional
    public void updateUsername(Long id, String username) {
        ensureUserExists(id);
        rootUserRepository.updateUsername(id, username);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, String email) {
        ensureUserExists(id);
        rootUserRepository.updateEmail(id, email);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(Long id, String phoneNumber) {
        ensureUserExists(id);
        rootUserRepository.updatePhoneNumber(id, phoneNumber);
    }

    @Override
    @Transactional
    public void updateHashPassword(Long id, String hashPassword) {
        ensureUserExists(id);
        rootUserRepository.updateHashPassword(id, hashPassword);
    }

    @Override
    @Transactional
    public void updateLastLoginDate(Long id, LocalDateTime lastLoginDate) {
        ensureUserExists(id);
        rootUserRepository.updateLastLoginDate(id, lastLoginDate);
    }

    @Override
    @Transactional
    public void updateAccountNonExpired(Long id, boolean accountNonExpired) {
        ensureUserExists(id);
        rootUserRepository.updateAccountNonExpired(id, accountNonExpired);
    }

    @Override
    @Transactional
    public void updateAccountNonLocked(Long id, boolean accountNonLocked) {
        ensureUserExists(id);
        rootUserRepository.updateAccountNonLocked(id, accountNonLocked);
    }

    @Override
    @Transactional
    public void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired) {
        ensureUserExists(id);
        rootUserRepository.updateCredentialsNonExpired(id, credentialsNonExpired);
    }

    @Override
    @Transactional
    public void updateEnabled(Long id, boolean enabled) {
        ensureUserExists(id);
        rootUserRepository.updateEnabled(id, enabled);
    }

    @Override
    @Transactional
    public void addRole(Long userId, RootUserRole role) {
        ensureUserExists(userId);
        rootUserRepository.addRole(userId, role.name());
    }
    
    @Override
    @Transactional
    public void addRoles(Long userId, EnumSet<RootUserRole> roles) {
        ensureUserExists(userId);
        for (RootUserRole role : roles) {
            rootUserRepository.addRole(userId, role.name());
        }
    }

    @Override
    @Transactional
    public void removeRole(Long userId, RootUserRole role) {
        ensureUserExists(userId);
        rootUserRepository.removeRole(userId, role.name());
    }

    @Override
    @Transactional
    public void removeRoles(Long userId, EnumSet<RootUserRole> roles) {
        ensureUserExists(userId);
        for (RootUserRole role : roles) {
            rootUserRepository.removeRole(userId, role.name());
        }
    }

    @Override
    @Transactional
    public void updateRoles(Long userId, EnumSet<RootUserRole> roles) {
        ensureUserExists(userId);

        rootUserRepository.deleteAllRoles(userId);
        for (RootUserRole role : roles) {
            rootUserRepository.addRole(userId, role.name());
        }
    }

    @Override
    @Transactional
    public void addAuthority(Long userId, RootUserAuthority authority) {
        ensureUserExists(userId);
        rootUserRepository.addAuthority(userId, authority.name());
    }

    @Override
    @Transactional
    public void addAuthorities(Long userId, EnumSet<RootUserAuthority> authorities) {
        ensureUserExists(userId);
        for (RootUserAuthority authority : authorities) {
            rootUserRepository.addAuthority(userId, authority.name());
        }
    }

    @Override
    @Transactional
    public void removeAuthority(Long userId, RootUserAuthority authority) {
        ensureUserExists(userId);
        rootUserRepository.removeAuthority(userId , authority.name());
    }

    @Override
    @Transactional
    public void removeAuthorities(Long userId, EnumSet<RootUserAuthority> authorities) {
        ensureUserExists(userId);
        for (RootUserAuthority authority : authorities) {
            rootUserRepository.removeAuthority(userId, authority.name());
        }
    }

    @Override
    @Transactional
    public void updateAuthorities(Long userId, EnumSet<RootUserAuthority> authorities) {
        ensureUserExists(userId);

        rootUserRepository.deleteAllAuthorities(userId);
        for (RootUserAuthority authority : authorities) {
            rootUserRepository.addAuthority(userId, authority.name());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        rootUserRepository.deleteById(id);
    }

    private void ensureUserExists(Long id) {
        if (!rootUserRepository.existsById(id)) {
            throw new UserNotFoundException("Пользователь не найден с id: " + id);
        }
    }

    private void existsByUsername(String username) {
        if (rootUserRepository.existsByUsername(username)) {
            throw new AlreadyExistsException("Пользователь с username " + username + " уже существует");
        }
    }

    private void existsByEmail(String email) {
        if (rootUserRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("Пользователь с email " + email + " уже существует");
        }
    }

    private void existsByPhoneNumber(String phoneNumber) {
        if (rootUserRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException("Пользователь с phoneNumber " + phoneNumber + " уже существует");
        }
    }
}
