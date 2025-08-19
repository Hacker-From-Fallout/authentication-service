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
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserAuthoritiesDto;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserAuthorityDto;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserCreateDto;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserFilterDto;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserRoleDto;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserRolesDto;
import com.ignat.chernyshov.auth.domain.dto.request.SellerUserUpdateDto;
import com.ignat.chernyshov.auth.domain.dto.request.UsernameDto;
import com.ignat.chernyshov.auth.domain.dto.response.SellerUserResponseDto;
import com.ignat.chernyshov.auth.domain.entities.SellerUser;
import com.ignat.chernyshov.auth.services.SellerUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller-user/")
public class SellerUserController {

    private final SellerUserService sellerUserService;

    @GetMapping
    public ResponseEntity<Page<SellerUserResponseDto>> getAllUsers(
            @ModelAttribute SellerUserFilterDto filters,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<SellerUser> pageResult = sellerUserService.findByFilters(filters, page, size);
        Page<SellerUserResponseDto> dtosPage = pageResult.map(SellerUserResponseDto::from);
        return ResponseEntity.ok(dtosPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        SellerUser sellerUser = sellerUserService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                SellerUserResponseDto.from(sellerUser));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        SellerUser sellerUser = sellerUserService.findByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(
                SellerUserResponseDto.from(sellerUser));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        SellerUser sellerUser = sellerUserService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(
                SellerUserResponseDto.from(sellerUser));
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable String phoneNumber) {
        SellerUser sellerUser = sellerUserService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(
                SellerUserResponseDto.from(sellerUser));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody SellerUserCreateDto dto, 
                            BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException error) {
                throw error;
            } else {
                throw new BindException(bindingResult);
            }
        }

        SellerUser sellerUser = sellerUserService.createUser(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(
                SellerUserResponseDto.from(sellerUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody SellerUserUpdateDto dto, 
                            BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException error) {
                throw error;
            } else {
                throw new BindException(bindingResult);
            }
        }

        SellerUser sellerUser = sellerUserService.updateUser(id, dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(
                SellerUserResponseDto.from(sellerUser));
    }

    @PatchMapping("/{id}/first-name")
    public ResponseEntity<?> updateFirstName(@PathVariable Long id, @Valid @RequestBody FirstNameDto dto) {
        sellerUserService.updateFirstName(id, dto.firstName());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/last-name")
    public ResponseEntity<?> updateLastName(@PathVariable Long id, @Valid @RequestBody LastNameDto dto) {
        sellerUserService.updateLastName(id, dto.lastName());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/username")
    public ResponseEntity<?> updateUsername(@PathVariable Long id, @Valid @RequestBody UsernameDto dto) {
        sellerUserService.updateUsername(id, dto.username());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/email")
    public ResponseEntity<?> updateEmail(@PathVariable Long id, @Valid @RequestBody EmailDto dto) {
        sellerUserService.updateEmail(id, dto.email());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/phone-number")
    public ResponseEntity<?> updatePhoneNumber(@PathVariable Long id, @Valid @RequestBody PhoneNumberDto dto) {
        sellerUserService.updatePhoneNumber(id, dto.phoneNumber());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @Valid @RequestBody PasswordDto dto) {
        sellerUserService.updateHashPassword(id, dto.password());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/last-login-date")
    public ResponseEntity<?> updateLastLoginDate(@PathVariable Long id, @Valid @RequestBody LastLoginDateDto dto) {
        sellerUserService.updateLastLoginDate(id, dto.lastLoginDate());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/accountNonExpired")
    public ResponseEntity<?> updateAccountNonExpired(@PathVariable Long id, @Valid @RequestBody AccountNonExpiredDto dto) {
        sellerUserService.updateAccountNonExpired(id, Boolean.TRUE.equals(dto.accountNonExpired().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/account-non-locked")
    public ResponseEntity<?> updateAccountNonLocked(@PathVariable Long id, @Valid @RequestBody AccountNonLockedDto dto) {
        sellerUserService.updateAccountNonLocked(id, Boolean.TRUE.equals(dto.accountNonLocked().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/credentials-non-expired")
    public ResponseEntity<?> updateCredentialsNonExpired(@PathVariable Long id, @Valid @RequestBody CredentialsNonExpiredDto dto) {
        sellerUserService.updateCredentialsNonExpired(id, Boolean.TRUE.equals(dto.credentialsNonExpired().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<?> updateEnabled(@PathVariable Long id, @Valid @RequestBody EnabledDto dto) {
        sellerUserService.updateEnabled(id, Boolean.TRUE.equals(dto.enabled().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/roles")
    public ResponseEntity<?> addRole(@PathVariable Long userId, @Valid @RequestBody SellerUserRoleDto dto) {
        sellerUserService.addRole(userId, dto.role());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/roles/batch")
    public ResponseEntity<?> addRoles(@PathVariable Long userId, @Valid @RequestBody SellerUserRolesDto dto) {
        sellerUserService.addRoles(userId, dto.roles());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/roles")
    public ResponseEntity<?> removeRole(@PathVariable Long userId, @Valid @RequestBody SellerUserRoleDto dto) {
        sellerUserService.removeRole(userId, dto.role());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/roles/batch")
    public ResponseEntity<?> removeRoles(@PathVariable Long userId, @Valid @RequestBody SellerUserRolesDto dto) {
        sellerUserService.removeRoles(userId, dto.roles());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/roles")
    public ResponseEntity<?> updateRoles(@PathVariable Long userId, @Valid @RequestBody SellerUserRolesDto dto) {
        sellerUserService.updateRoles(userId, dto.roles());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/authorities")
    public ResponseEntity<?> addAuthority(@PathVariable Long userId, @Valid @RequestBody SellerUserAuthorityDto dto) {
        sellerUserService.addAuthority(userId, dto.authority());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/authorities/batch")
    public ResponseEntity<?> addAuthorities(@PathVariable Long userId, @Valid @RequestBody SellerUserAuthoritiesDto dto) {
        sellerUserService.addAuthorities(userId, dto.authorities());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/authorities")
    public ResponseEntity<?> removeAuthority(@PathVariable Long userId, @Valid @RequestBody SellerUserAuthorityDto dto) {
        sellerUserService.removeAuthority(userId, dto.authority());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}/authorities/batch")
    public ResponseEntity<?> removeAuthorities(@PathVariable Long userId, @Valid @RequestBody SellerUserAuthoritiesDto dto) {
        sellerUserService.removeAuthorities(userId, dto.authorities());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/authorities")
    public ResponseEntity<?> updateAuthorities(@PathVariable Long userId, @Valid @RequestBody SellerUserAuthoritiesDto dto) {
        sellerUserService.updateAuthorities(userId, dto.authorities());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        sellerUserService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
