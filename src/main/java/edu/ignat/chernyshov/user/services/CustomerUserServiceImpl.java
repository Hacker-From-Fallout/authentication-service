package edu.ignat.chernyshov.user.services;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;

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
    public CustomerUser createUser(EnumSet<CustomerUserRole> roles, 
                            EnumSet<CustomerUserAuthority> authorities,
                            boolean accountNonExpired, boolean accountNonLocked, 
                            boolean credentialsNonExpired, boolean enabled, 
                            String firstName, String lastName, String username, 
                            String email, String phoneNumber, String password) {
        existsByUsername(username);
        existsByEmail(email);
        existsByPhoneNumber(phoneNumber);

        CustomerUser customerUser = CustomerUser.builder()
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

        return customerUserRepository.save(customerUser);
    }

    @Override
    @Transactional()
    public CustomerUser updateUser(Long id, EnumSet<CustomerUserRole> roles, 
                            EnumSet<CustomerUserAuthority> authorities, Boolean accountNonExpired, 
                            Boolean accountNonLocked, Boolean credentialsNonExpired, Boolean enabled, 
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
        if (accountNonExpired != null) {
            customerUser.setAccountNonExpired(accountNonExpired.booleanValue());
        }
        if (accountNonLocked != null) {
            customerUser.setAccountNonLocked(accountNonLocked.booleanValue());
        }
        if (credentialsNonExpired != null) {
            customerUser.setCredentialsNonExpired(credentialsNonExpired.booleanValue());
        }
        if (enabled != null) {
            customerUser.setEnabled(enabled.booleanValue());
        }
        if (firstName != null) {
            customerUser.setFirstName(firstName);
        }
        if (lastName != null) {
            customerUser.setLastName(lastName);
        }
        if (username != null) {
            existsByUsername(username);
            customerUser.setUsername(username);;
        }
        if (email != null) {
            existsByEmail(email);
            customerUser.setEmail(email);;
        }
        if (phoneNumber != null) {
            existsByPhoneNumber(phoneNumber);
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
    @Transactional
    public void updateRoles(Long id, EnumSet<CustomerUserRole> roles) {
        CustomerUser customerUser = customerUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));

        customerUser.setRoles(roles);
        customerUserRepository.save(customerUser);
    }

    @Override
    @Transactional
    public void updateAuthorities(Long id, EnumSet<CustomerUserAuthority> authorities) {
        CustomerUser customerUser = customerUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден с id: " + id));
        
        customerUser.setAuthorities(authorities);
        customerUserRepository.save(customerUser);
    }

    @Override
    @Transactional
    public void updateAccountNonExpired(Long id, boolean accountNonExpired) {
        customerUserRepository.updateAccountNonExpired(id, accountNonExpired);
    }

    @Override
    @Transactional
    public void updateAccountNonLocked(Long id, boolean accountNonLocked) {
        customerUserRepository.updateAccountNonLocked(id, accountNonLocked);
    }

    @Override
    @Transactional
    public void updateCredentialsNonExpired(Long id, boolean credentialsNonExpired) {
        customerUserRepository.updateCredentialsNonExpired(id, credentialsNonExpired);
    }

    @Override
    @Transactional
    public void updateEnabled(Long id, boolean enabled) {
        customerUserRepository.updateEnabled(id, enabled);
    }

    @Override
    @Transactional
    public void addAuthority(Long userId, CustomerUserAuthority authority) {
        customerUserRepository.addAuthority(userId, authority.name());
    }

    @Override
    @Transactional
    public void addAuthorities(Long userId, EnumSet<CustomerUserAuthority> authorities) {
        for (CustomerUserAuthority authority : authorities) {
            customerUserRepository.addAuthority(userId, authority.name());
        }
    }

    @Override
    @Transactional
    public void removeAuthority(Long userId, CustomerUserAuthority authority) {
        customerUserRepository.removeAuthority(userId , authority.name());
    }

    @Override
    @Transactional
    public void removeAuthorities(Long userId, EnumSet<CustomerUserAuthority> authorities) {
        for (CustomerUserAuthority authority : authorities) {
            customerUserRepository.removeAuthority(userId, authority.name());
        }
    }

    @Override
    @Transactional
    public void addRole(Long userId, CustomerUserRole role) {
        customerUserRepository.addRole(userId, role.name());
    }
    
    @Override
    @Transactional
    public void addRoles(Long userId, EnumSet<CustomerUserRole> roles) {
        for (CustomerUserRole role : roles) {
            customerUserRepository.addRole(userId, role.name());
        }
    }

    @Override
    @Transactional
    public void removeRole(Long userId, CustomerUserRole role) {
        customerUserRepository.removeRole(userId, role.name());
    }

    @Override
    @Transactional
    public void removeRoles(Long userId, EnumSet<CustomerUserRole> roles) {
        for (CustomerUserRole role : roles) {
            customerUserRepository.removeRole(userId, role.name());
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id){
        customerUserRepository.deleteById(id);
    }

    private void existsByUsername(String username) {
        if (customerUserRepository.existsByUsername(username)) {
            throw new AlreadyExistsException("Пользователь с username " + username + "уже существует");
        }
    }

    private void existsByEmail(String email) {
        if (customerUserRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("Пользователь с email " + email + "уже существует");
        }
    }

    private void existsByPhoneNumber(String phoneNumber) {
        if (customerUserRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException("Пользователь с phoneNumber " + phoneNumber + "уже существует");
        }
    }
}
