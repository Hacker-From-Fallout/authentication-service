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

import com.ignat.chernyshov.auth.domain.authorities.SellerUserAuthority;
import com.ignat.chernyshov.auth.domain.authorities.SellerUserRole;
import com.ignat.chernyshov.auth.domain.dto.kafka.SellerProfileCreateDto;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserCreateDto;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserFilterDto;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserUpdateDto;
import com.ignat.chernyshov.auth.domain.entities.SellerUser;
import com.ignat.chernyshov.auth.exception.exceptions.AlreadyExistsException;
import com.ignat.chernyshov.auth.exception.exceptions.UserNotFoundException;
import com.ignat.chernyshov.auth.producers.SellerUserProducer;
import com.ignat.chernyshov.auth.repositories.SellerUserRepository;
import com.ignat.chernyshov.auth.repositories.specifications.SellerUserSpecifications;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultSellerUserService implements SellerUserService {

    private final SellerUserRepository sellerUserRepository;
    private final SellerUserProducer sellerUserProducer;
    private final PasswordEncoder bCyPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public Page<SellerUser> findByFilters(SellerUserFilterDto filters, int page, int size) {
        Specification<SellerUser> specification = (seller, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (filters.username() != null && !filters.username().isEmpty()) {
            specification = specification.and(SellerUserSpecifications.hasUsername(filters.username()));
        }
        if (filters.email() != null && !filters.email().isEmpty()) {
            specification = specification.and(SellerUserSpecifications.hasEmail(filters.email()));
        }
        if (filters.phoneNumber() != null && !filters.phoneNumber().isEmpty()) {
            specification = specification.and(SellerUserSpecifications.hasPhoneNumber(filters.phoneNumber()));
        }

        Pageable pageable = PageRequest.of(page, size);
        return sellerUserRepository.findAll(specification, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public SellerUser findById(Long id) {
        return sellerUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public SellerUser findByUsername(String username) {
        return sellerUserRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с username: " + username));
    
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
    @Transactional
    public SellerUser createUser(SellerUserCreateDto sellerDto) {
        validateUniqueFields(sellerDto);

        SellerUser sellerUser = SellerUser(sellerDto);
        sellerUser = sellerUserRepository.save(sellerUser);
        
        SellerProfileCreateDto profileDto = new SellerProfileCreateDto(
            sellerUser.getId(),
            sellerDto.firstName(),
            sellerDto.lastName(),
            sellerDto.username(),
            sellerDto.email(),
            sellerDto.phoneNumber());

        sellerUserProducer.createProfile(profileDto);

        return sellerUser;
    }

    private void validateUniqueFields(SellerUserCreateDto dto) {
        existsByUsername(dto.username());
        existsByEmail(dto.email());
        existsByPhoneNumber(dto.phoneNumber());
    }

    private SellerUser SellerUser(SellerUserCreateDto dto) {
        return SellerUser.builder()
                .organizationId(dto.organizationId())
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
    public SellerUser updateUser(Long id, SellerUserUpdateDto dto) {
        SellerUser sellerUser = sellerUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

            if (dto.username() != null) {
                existsByUsername(dto.username());
                sellerUser.setUsername(dto.username());
                sellerUserProducer.updateUsername(id, dto.username());
            }
            if (dto.email() != null) {
                existsByEmail(dto.email());
                sellerUser.setEmail(dto.email());
                sellerUserProducer.updateEmail(id, dto.email());
            }
            if (dto.phoneNumber() != null) {
                existsByPhoneNumber(dto.phoneNumber());
                sellerUser.setPhoneNumber(dto.phoneNumber());
                sellerUserProducer.updatePhoneNumber(id, dto.phoneNumber());
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
    public void updateUsername(Long id, String username) {
        existsByUsername(username);
        ensureUserExists(id);
        sellerUserRepository.updateUsername(id, username);
        sellerUserProducer.updateUsername(id, username);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, String email) {
        existsByEmail(email);
        ensureUserExists(id);
        sellerUserRepository.updateEmail(id, email);
        sellerUserProducer.updateEmail(id, email);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(Long id, String phoneNumber) {
        existsByPhoneNumber(phoneNumber);
        ensureUserExists(id);
        sellerUserRepository.updatePhoneNumber(id, phoneNumber);
        sellerUserProducer.updatePhoneNumber(id, phoneNumber);
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
    public void updateRoles(Long userId, EnumSet<SellerUserRole> roles) {
        ensureUserExists(userId);

        sellerUserRepository.deleteAllRoles(userId);
        for (SellerUserRole role : roles) {
            sellerUserRepository.addRole(userId, role.name());
        }
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
    public void updateAuthorities(Long userId, EnumSet<SellerUserAuthority> authorities) {
        ensureUserExists(userId);

        sellerUserRepository.deleteAllAuthorities(userId);
        for (SellerUserAuthority authority : authorities) {
            sellerUserRepository.addAuthority(userId, authority.name());
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
