package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.CustomerUserRole;
import edu.ignat.chernyshov.user.domain.entities.CustomerUser;
import edu.ignat.chernyshov.user.exception.exceptions.AlreadyExistsException;
import edu.ignat.chernyshov.user.exception.exceptions.UserNotFoundException;
import edu.ignat.chernyshov.user.repositories.CustomerUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerUserServiceImpl implements CustomerUserService {

    private final CustomerUserRepository customerUserRepository;
    private final PasswordEncoder bCyPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<CustomerUser> findAll() {
        return customerUserRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerUser findById(Long id) {
        return customerUserRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerUser findByEmail(String email) {
        return customerUserRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с email: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerUser findByPhoneNumber(String phoneNumber) {
        return customerUserRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с phoneNumber: " + phoneNumber));
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerUser findByUsername(String username) {
        return customerUserRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с username: " + username));
    
    }

    @Override
    @Transactional()
    public CustomerUser createUser(Set<CustomerUserRole> roles, Set<CustomerUserAuthority> authorities, 
                            String firstName, String lastName, String username, String email,
                            String phoneNumber, String password) {
        existsByIdentifiers(username, email, phoneNumber);

        CustomerUser customerUser = CustomerUser.builder()
                .roles(roles)
                .authorities(authorities)
                .firstName(firstName)
                .lastName(lastName)
                .username(username)
                .email(email)
                .phoneNumber(phoneNumber)
                .hashPassword(bCyPasswordEncoder.encode(password))
                .build();

        return customerUserRepository.save(customerUser);
    }

    @Override
    @Transactional()
    public CustomerUser updateUser(Long id, Set<CustomerUserRole> roles, Set<CustomerUserAuthority> authorities, 
                            String firstName, String lastName, String username, String email, 
                            String phoneNumber, String password, LocalDateTime lastLoginDate) {
        CustomerUser customerUser = customerUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

        if(roles != null) {
            customerUser.setRoles(roles);
        }
        if (authorities != null) {
            customerUser.setAuthorities(authorities);
        }
        if (firstName != null) {
            customerUser.setFirstName(firstName);
        }
        if (lastName != null) {
            customerUser.setLastName(lastName);
        }
        if (username != null) {
            customerUser.setUsername(username);;
        }
        if (email != null) {
            customerUser.setEmail(email);;
        }
        if (phoneNumber != null) {
            customerUser.setPhoneNumber(phoneNumber);
        }
        if (password != null) {
            customerUser.setHashPassword(bCyPasswordEncoder.encode(password));
        }
        if (lastLoginDate != null) {
            customerUser.setLastLoginDate(lastLoginDate);
        }

        return customerUserRepository.save(customerUser);
    }

    @Override
    @Transactional
    public void updateFirstName(Long id, String firstName) {
        customerUserRepository.updateFirstName(id, firstName);
    }

    @Override
    @Transactional
    public void updateLastName(Long id, String lastName) {
        customerUserRepository.updateLastName(id, lastName);
    }

    @Override
    @Transactional
    public void updatePhoneNumber(Long id, String phoneNumber) {
        customerUserRepository.updatePhoneNumber(id, phoneNumber);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, String email) {
        customerUserRepository.updateEmail(id, email);
    }

    @Override
    @Transactional
    public void updateUsername(Long id, String username) {
        customerUserRepository.updateUsername(id, username);
    }

    @Override
    @Transactional
    public void updateHashPassword(Long id, String hashPassword) {
        customerUserRepository.updateHashPassword(id, hashPassword);
    }

    @Override
    @Transactional
    public void updateLastLoginDate(Long id, LocalDateTime lastLoginDate) {
        customerUserRepository.updateLastLoginDate(id, lastLoginDate);
    }

    @Override
    public void updateRoles(Long id, Set<CustomerUserRole> roles) {
        throw new UnsupportedOperationException("Unimplemented method 'updateRoles'");
    }
    @Override
    public void updateAuthorities(Long id, Set<CustomerUserAuthority> authorities) {
        throw new UnsupportedOperationException("Unimplemented method 'updateAuthorities'");
    }

    @Override
    @Transactional
    public void addAuthority(Long userId, String authority) {
        customerUserRepository.addAuthority(userId, authority);
    }

    @Override
    public void addAuthorities(Long userId, Set<CustomerUserAuthority> authorities) {
        throw new UnsupportedOperationException("Unimplemented method 'addAuthorities'");
    }

    @Transactional
    public void removeAuthority(Long userId, String authority){
        customerUserRepository.removeAuthority(userId , authority);
    }

    @Override
    public void removeAuthorities(Long userId, Set<CustomerUserAuthority> authorities) {
        throw new UnsupportedOperationException("Unimplemented method 'removeAuthorities'");
    }

    @Override
    public void addRole(Long userId, String role) {
        throw new UnsupportedOperationException("Unimplemented method 'addRole'");
    }
    
    @Override
    public void addRoles(Long userId, Set<CustomerUserRole> roles) {
        throw new UnsupportedOperationException("Unimplemented method 'addRoles'");
    }

    @Override
    public void removeRole(Long userId, String role) {
        throw new UnsupportedOperationException("Unimplemented method 'removeRole'");
    }

    @Override
    public void removeRoles(Long userId, Set<CustomerUserRole> roles) {
        throw new UnsupportedOperationException("Unimplemented method 'removeRoles'");
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        customerUserRepository.deleteById(id);
    }

    private void existsByIdentifiers(String username, String email, String phoneNumber) {
        if (customerUserRepository.existsByUsername(username)) {
            throw new AlreadyExistsException("Пользователь с username " + username + "уже существует");
        }
        if (customerUserRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("Пользователь с email " + email + "уже существует");
        }
        if (customerUserRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException("Пользователь с phoneNumber " + phoneNumber + "уже существует");
        }
    }
}
