package edu.ignat.chernyshov.user.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ignat.chernyshov.user.domain.dto.request.AccountNonExpiredDto;
import edu.ignat.chernyshov.user.domain.dto.request.AccountNonLockedDto;
import edu.ignat.chernyshov.user.domain.dto.request.CredentialsNonExpiredDto;
import edu.ignat.chernyshov.user.domain.dto.request.EmailDto;
import edu.ignat.chernyshov.user.domain.dto.request.EnabledDto;
import edu.ignat.chernyshov.user.domain.dto.request.FirstNameDto;
import edu.ignat.chernyshov.user.domain.dto.request.LastLoginDateDto;
import edu.ignat.chernyshov.user.domain.dto.request.LastNameDto;
import edu.ignat.chernyshov.user.domain.dto.request.PasswordDto;
import edu.ignat.chernyshov.user.domain.dto.request.PhoneNumberDto;
import edu.ignat.chernyshov.user.domain.dto.request.SellerUserAuthoritiesDto;
import edu.ignat.chernyshov.user.domain.dto.request.SellerUserAuthorityDto;
import edu.ignat.chernyshov.user.domain.dto.request.SellerUserCreateDto;
import edu.ignat.chernyshov.user.domain.dto.request.SellerUserRoleDto;
import edu.ignat.chernyshov.user.domain.dto.request.SellerUserRolesDto;
import edu.ignat.chernyshov.user.domain.dto.request.SellerUserUpdateDto;
import edu.ignat.chernyshov.user.domain.dto.request.UsernameDto;
import edu.ignat.chernyshov.user.domain.dto.response.SellerUserResponseDto;
import edu.ignat.chernyshov.user.domain.entities.SellerUser;
import edu.ignat.chernyshov.user.services.SellerUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller-user/")
public class SellerUserController {

    private final SellerUserService sellerUserService;

    @GetMapping("/users")
    public ResponseEntity<List<SellerUserResponseDto>> getUsers() {
        List<SellerUser> users = sellerUserService.findAll();

        List<SellerUserResponseDto> dtos = users.stream()
                .map(SellerUserResponseDto::from)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/by-id/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId) {
        SellerUser sellerUser = sellerUserService.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(
                SellerUserResponseDto.from(sellerUser));
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
        SellerUser sellerUser = sellerUserService.findByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(
                SellerUserResponseDto.from(sellerUser));
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
        SellerUser sellerUser = sellerUserService.findByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(
                SellerUserResponseDto.from(sellerUser));
    }

    @GetMapping("/by-phone/{phoneNumber}")
    public ResponseEntity<?> getUserByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        SellerUser sellerUser = sellerUserService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(
                SellerUserResponseDto.from(sellerUser));
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@Valid @RequestBody SellerUserCreateDto dto, 
                            BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException error) {
                throw error;
            } else {
                throw new BindException(bindingResult);
            }
        }

        SellerUser SellerUser = sellerUserService.createUser(dto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(
                SellerUserResponseDto.from(SellerUser));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @Valid @RequestBody SellerUserUpdateDto dto, 
                            BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException error) {
                throw error;
            } else {
                throw new BindException(bindingResult);
            }
        }

        SellerUser SellerUser = sellerUserService.updateUser(userId, dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(
                SellerUserResponseDto.from(SellerUser));
    }

    @PatchMapping("/first-name/{userId}")
    public ResponseEntity<?> updateFirstName(@PathVariable Long userId, @Valid @RequestBody FirstNameDto dto) {
        sellerUserService.updateFirstName(userId, dto.firstName());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/last-name/{userId}")
    public ResponseEntity<?> updateLastName(@PathVariable Long userId, @Valid @RequestBody LastNameDto dto) {
        sellerUserService.updateLastName(userId, dto.lastName());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/username/{userId}")
    public ResponseEntity<?> updateUsername(@PathVariable Long userId, @Valid @RequestBody UsernameDto dto) {
        sellerUserService.updateUsername(userId, dto.username());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/email/{userId}")
    public ResponseEntity<?> updateEmail(@PathVariable Long userId, @Valid @RequestBody EmailDto dto) {
        sellerUserService.updateEmail(userId, dto.email());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/phone-number/{userId}")
    public ResponseEntity<?> updatePhoneNumber(@PathVariable Long userId, @Valid @RequestBody PhoneNumberDto dto) {
        sellerUserService.updatePhoneNumber(userId, dto.phoneNumber());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/password/{userId}")
    public ResponseEntity<?> updatePassword(@PathVariable Long userId, @Valid @RequestBody PasswordDto dto) {
        sellerUserService.updateHashPassword(userId, dto.password());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/last-login-date/{userId}")
    public ResponseEntity<?> updateLastLoginDate(@PathVariable Long userId, @Valid @RequestBody LastLoginDateDto dto) {
        sellerUserService.updateLastLoginDate(userId, dto.lastLoginDate());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/roles/{userId}")
    public ResponseEntity<?> updateRoles(@PathVariable Long userId, @Valid @RequestBody SellerUserRolesDto dto) {
        sellerUserService.updateRoles(userId, dto.roles());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/authorities/{userId}")
    public ResponseEntity<?> updateAuthorities(@PathVariable Long userId, @Valid @RequestBody SellerUserAuthoritiesDto dto) {
        sellerUserService.updateAuthorities(userId, dto.authorities());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/account-non-expired/{userId}")
    public ResponseEntity<?> updateAccountNonExpired(@PathVariable Long userId, @Valid @RequestBody AccountNonExpiredDto dto) {
        sellerUserService.updateAccountNonExpired(userId, Boolean.TRUE.equals(dto.accountNonExpired().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/account-non-locked/{userId}")
    public ResponseEntity<?> updateAccountNonLocked(@PathVariable Long userId, @Valid @RequestBody AccountNonLockedDto dto) {
        sellerUserService.updateAccountNonLocked(userId, Boolean.TRUE.equals(dto.accountNonLocked().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/credentials-non-expired/{userId}")
    public ResponseEntity<?> updateCredentialsNonExpired(@PathVariable Long userId, @Valid @RequestBody CredentialsNonExpiredDto dto) {
        sellerUserService.updateCredentialsNonExpired(userId, Boolean.TRUE.equals(dto.credentialsNonExpired().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/enabled/{userId}")
    public ResponseEntity<?> updateEnabled(@PathVariable Long userId, @Valid @RequestBody EnabledDto dto) {
        sellerUserService.updateEnabled(userId, Boolean.TRUE.equals(dto.enabled().booleanValue()));
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/add-authority/{userId}")
    public ResponseEntity<?> addAuthority(@PathVariable Long userId, @Valid @RequestBody SellerUserAuthorityDto dto) {
        sellerUserService.addAuthority(userId, dto.authority());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/add-authorities/{userId}")
    public ResponseEntity<?> addAuthorities(@PathVariable Long userId, @Valid @RequestBody SellerUserAuthoritiesDto dto) {
        sellerUserService.addAuthorities(userId, dto.authorities());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/remove-authority/{userId}")
    public ResponseEntity<?> removeAuthority(@PathVariable Long userId, @Valid @RequestBody SellerUserAuthorityDto dto) {
        sellerUserService.removeAuthority(userId, dto.authority());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/remove-authorities/{userId}")
    public ResponseEntity<?> removeAuthorities(@PathVariable Long userId, @Valid @RequestBody SellerUserAuthoritiesDto dto) {
        sellerUserService.removeAuthorities(userId, dto.authorities());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/add-role/{userId}")
    public ResponseEntity<?> addRole(@PathVariable Long userId, @Valid @RequestBody SellerUserRoleDto dto) {
        sellerUserService.addRole(userId, dto.role());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/add-roles/{userId}")
    public ResponseEntity<?> addRoles(@PathVariable Long userId, @Valid @RequestBody SellerUserRolesDto dto) {
        sellerUserService.addRoles(userId, dto.roles());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/remove-role/{userId}")
    public ResponseEntity<?> removeRole(@PathVariable Long userId, @Valid @RequestBody SellerUserRoleDto dto) {
        sellerUserService.removeRole(userId, dto.role());
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/remove-roles/{userId}")
    public ResponseEntity<?> removeRoles(@PathVariable Long userId, @Valid @RequestBody SellerUserRolesDto dto) {
        sellerUserService.removeRoles(userId, dto.roles());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        sellerUserService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
