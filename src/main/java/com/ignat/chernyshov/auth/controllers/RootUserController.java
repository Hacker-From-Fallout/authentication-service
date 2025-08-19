package com.ignat.chernyshov.auth.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ignat.chernyshov.auth.domain.dto.request.AccountNonExpiredDto;
import com.ignat.chernyshov.auth.domain.dto.request.AccountNonLockedDto;
import com.ignat.chernyshov.auth.domain.dto.request.CredentialsNonExpiredDto;
import com.ignat.chernyshov.auth.domain.dto.request.EmailDto;
import com.ignat.chernyshov.auth.domain.dto.request.EnabledDto;
import com.ignat.chernyshov.auth.domain.dto.request.FirstNameDto;
import com.ignat.chernyshov.auth.domain.dto.request.LastLoginDateDto;
import com.ignat.chernyshov.auth.domain.dto.request.LastNameDto;
import com.ignat.chernyshov.auth.domain.dto.request.PasswordDto;
import com.ignat.chernyshov.auth.domain.dto.request.PhoneNumberDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserAuthoritiesDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserAuthorityDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserCreateDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserFilterDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserRoleDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserRolesDto;
import com.ignat.chernyshov.auth.domain.dto.request.RootUserUpdateDto;
import com.ignat.chernyshov.auth.domain.dto.request.UsernameDto;
import com.ignat.chernyshov.auth.domain.dto.response.RootUserResponseDto;
import com.ignat.chernyshov.auth.domain.entities.RootUser;
import com.ignat.chernyshov.auth.services.RootUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/root-users/")
public class RootUserController {

    private final RootUserService rootUserService;

    @GetMapping
    public ResponseEntity<Page<RootUserResponseDto>> getAllUsers(
            @ModelAttribute RootUserFilterDto filters,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<RootUser> pageResult = rootUserService.findByFilters(filters, page, size);
        Page<RootUserResponseDto> dtosPage = pageResult.map(RootUserResponseDto::from);
        return ResponseEntity.ok(dtosPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        RootUser rootUser = rootUserService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                RootUserResponseDto.from(rootUser));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        RootUser rootUser = rootUserService.findByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(
                RootUserResponseDto.from(rootUser));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        RootUser rootUser = rootUserService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(
                RootUserResponseDto.from(rootUser));
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        RootUser rootUser = rootUserService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(
                RootUserResponseDto.from(rootUser));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody RootUserCreateDto dto, 
                            BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException error) {
                throw error;
            } else {
                throw new BindException(bindingResult);
            }
        }

        RootUser rootUser = rootUserService.createUser(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(
                RootUserResponseDto.from(rootUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody RootUserUpdateDto dto, 
                            BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException error) {
                throw error;
            } else {
                throw new BindException(bindingResult);
            }
        }

        RootUser rootUser = rootUserService.updateUser(id, dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(
                RootUserResponseDto.from(rootUser));
    }

    @PatchMapping("/{id}/first-name")
    public ResponseEntity<?> updateFirstName(@PathVariable Long id, @Valid @RequestBody FirstNameDto dto) {
        rootUserService.updateFirstName(id, dto.firstName());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/last-name")
    public ResponseEntity<?> updateLastName(@PathVariable Long id, @Valid @RequestBody LastNameDto dto) {
        rootUserService.updateLastName(id, dto.lastName());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/username")
    public ResponseEntity<?> updateUsername(@PathVariable Long id, @Valid @RequestBody UsernameDto dto) {
        rootUserService.updateUsername(id, dto.username());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<?> updateEmail(@PathVariable Long id, @Valid @RequestBody EmailDto dto) {
        rootUserService.updateEmail(id, dto.email());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/phone-number")
    public ResponseEntity<?> updatePhoneNumber(@PathVariable Long id, @Valid @RequestBody PhoneNumberDto dto) {
        rootUserService.updatePhoneNumber(id, dto.phoneNumber());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @Valid @RequestBody PasswordDto dto) {
        rootUserService.updateHashPassword(id, dto.password());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/last-login-date")
    public ResponseEntity<?> updateLastLoginDate(@PathVariable Long id, @Valid @RequestBody LastLoginDateDto dto) {
        rootUserService.updateLastLoginDate(id, dto.lastLoginDate());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/accountNonExpired")
    public ResponseEntity<?> updateAccountNonExpired(@PathVariable Long id, @Valid @RequestBody AccountNonExpiredDto dto) {
        rootUserService.updateAccountNonExpired(id, Boolean.TRUE.equals(dto.accountNonExpired().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/account-non-locked")
    public ResponseEntity<?> updateAccountNonLocked(@PathVariable Long id, @Valid @RequestBody AccountNonLockedDto dto) {
        rootUserService.updateAccountNonLocked(id, Boolean.TRUE.equals(dto.accountNonLocked().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/credentials-non-expired")
    public ResponseEntity<?> updateCredentialsNonExpired(@PathVariable Long id, @Valid @RequestBody CredentialsNonExpiredDto dto) {
        rootUserService.updateCredentialsNonExpired(id, Boolean.TRUE.equals(dto.credentialsNonExpired().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<?> updateEnabled(@PathVariable Long id, @Valid @RequestBody EnabledDto dto) {
        rootUserService.updateEnabled(id, Boolean.TRUE.equals(dto.enabled().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/roles")
    public ResponseEntity<?> addRole(@PathVariable Long userId, @Valid @RequestBody RootUserRoleDto dto) {
        rootUserService.addRole(userId, dto.role());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/roles/batch")
    public ResponseEntity<?> addRoles(@PathVariable Long userId, @Valid @RequestBody RootUserRolesDto dto) {
        rootUserService.addRoles(userId, dto.roles());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/roles")
    public ResponseEntity<?> removeRole(@PathVariable Long userId, @Valid @RequestBody RootUserRoleDto dto) {
        rootUserService.removeRole(userId, dto.role());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/roles/batch")
    public ResponseEntity<?> removeRoles(@PathVariable Long userId, @Valid @RequestBody RootUserRolesDto dto) {
        rootUserService.removeRoles(userId, dto.roles());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/roles")
    public ResponseEntity<?> updateRoles(@PathVariable Long userId, @Valid @RequestBody RootUserRolesDto dto) {
        rootUserService.updateRoles(userId, dto.roles());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/authorities")
    public ResponseEntity<?> addAuthority(@PathVariable Long userId, @Valid @RequestBody RootUserAuthorityDto dto) {
        rootUserService.addAuthority(userId, dto.authority());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/authorities/batch")
    public ResponseEntity<?> addAuthorities(@PathVariable Long userId, @Valid @RequestBody RootUserAuthoritiesDto dto) {
        rootUserService.addAuthorities(userId, dto.authorities());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/authorities")
    public ResponseEntity<?> removeAuthority(@PathVariable Long userId, @Valid @RequestBody RootUserAuthorityDto dto) {
        rootUserService.removeAuthority(userId, dto.authority());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/authorities/batch")
    public ResponseEntity<?> removeAuthorities(@PathVariable Long userId, @Valid @RequestBody RootUserAuthoritiesDto dto) {
        rootUserService.removeAuthorities(userId, dto.authorities());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/authorities")
    public ResponseEntity<?> updateAuthorities(@PathVariable Long userId, @Valid @RequestBody RootUserAuthoritiesDto dto) {
        rootUserService.updateAuthorities(userId, dto.authorities());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        rootUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
