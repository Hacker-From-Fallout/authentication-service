package com.ignat.chernyshov.auth.services;

import java.time.LocalDateTime;
import java.util.EnumSet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ignat.chernyshov.auth.domain.authorities.RootUserAuthority;
import com.ignat.chernyshov.auth.domain.authorities.RootUserRole;
import com.ignat.chernyshov.auth.domain.dto.kafka.RootProfileCreateDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserCreateDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserFilterDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserUpdateDto;
import com.ignat.chernyshov.auth.domain.entities.RootUser;
import com.ignat.chernyshov.auth.exception.exceptions.AlreadyExistsException;
import com.ignat.chernyshov.auth.exception.exceptions.UserNotFoundException;
import com.ignat.chernyshov.auth.producers.RootUserProducer;
import com.ignat.chernyshov.auth.repositories.RootUserRepository;
import com.ignat.chernyshov.auth.repositories.specifications.RootUserSpecifications;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultRootUserService implements RootUserService {

    private final RootUserRepository rootUserRepository;
    private final RootUserProducer rootUserProducer;
    private final PasswordEncoder bCyPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Page<RootUser> findByFilters(RootUserFilterDto filters, int page, int size) {
        Specification<RootUser> specification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

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
    public RootUser createUser(RootUserCreateDto rootDto) {
        validateUniqueFields(rootDto);

        RootUser rootUser = RootUser(rootDto);
        rootUser = rootUserRepository.save(rootUser);
        
        RootProfileCreateDto profileDto = new RootProfileCreateDto(
            rootUser.getId(),
            rootDto.firstName(),
            rootDto.lastName(),
            rootDto.username(),
            rootDto.email(),
            rootDto.phoneNumber());

        rootUserProducer.createProfile(profileDto);

        return rootUser;
    }

    private void validateUniqueFields(RootUserCreateDto dto) {
        existsByUsername(dto.username());
        existsByEmail(dto.email());
        existsByPhoneNumber(dto.phoneNumber());
    }

    private RootUser RootUser(RootUserCreateDto dto) {
        return RootUser.builder()
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
    }

    @Override
    @Transactional
    public RootUser updateUser(Long id, RootUserUpdateDto dto) {
        RootUser rootUser = rootUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

        if (dto.username() != null) {
            existsByUsername(dto.username());
            rootUser.setUsername(dto.username());
            rootUserProducer.updateUsername(id, dto.username());
        }
        if (dto.email() != null) {
            existsByEmail(dto.email());
            rootUser.setEmail(dto.email());
            rootUserProducer.updateEmail(id, dto.email());
        }
        if (dto.phoneNumber() != null) {
            existsByPhoneNumber(dto.phoneNumber());
            rootUser.setPhoneNumber(dto.phoneNumber());
            rootUserProducer.updatePhoneNumber(id, dto.phoneNumber());
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
    public void updateUsername(Long id, String username) {
        ensureUserExists(id);
        existsByUsername(username);
        rootUserRepository.updateUsername(id, username);
        rootUserProducer.updateUsername(id, username);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, String email) {
        ensureUserExists(id);
        existsByEmail(email);
        rootUserRepository.updateEmail(id, email);
        rootUserProducer.updateEmail(id, email);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(Long id, String phoneNumber) {
        ensureUserExists(id);
        existsByPhoneNumber(phoneNumber);
        rootUserRepository.updatePhoneNumber(id, phoneNumber);
        rootUserProducer.updatePhoneNumber(id, phoneNumber);
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
