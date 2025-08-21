package com.ignat.chernyshov.auth;

import java.time.LocalDateTime;
import java.util.EnumSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ignat.chernyshov.auth.domain.authorities.CustomerUserAuthority;
import com.ignat.chernyshov.auth.domain.authorities.CustomerUserRole;
import com.ignat.chernyshov.auth.domain.dto.request.CustomerUserCreateDto;
import com.ignat.chernyshov.auth.domain.entities.CustomerUser;
import com.ignat.chernyshov.auth.services.DefaultCustomerUserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MyCommandLineRunner implements CommandLineRunner {

    //private final CustomerUserRepository customerUserRepository;

    private final DefaultCustomerUserService customerUserService;

    @Override
    public void run(String... args) throws Exception {

        // runDemo();
        // customerUserRepository.deleteAll();
    }

    public void runDemo() {
        // Создаем нового пользователя
        CustomerUser newUser = customerUserService.createUser(new CustomerUserCreateDto("John", "Doe", "johndoe", "john@example.com", "+1234567890", "password123",
                EnumSet.of(CustomerUserRole.ROLE_CUSTOMER),
                EnumSet.of(CustomerUserAuthority.CUSTOMER_READ_PRODUCT, CustomerUserAuthority.CUSTOMER_MAKE_AN_ORDER),
                true, // accountNonExpired
                true, // accountNonLocked
                true, // credentialsNonExpired
                true // enabled
                ));
        System.out.println("Создан пользователь: " + newUser);

        Long userId = newUser.getId();

        // Получение пользователя по ID
        CustomerUser fetchedById = customerUserService.findById(userId);
        System.out.println("Получен по ID: " + fetchedById);

        // Получение пользователя по username
        CustomerUser fetchedByUsername = customerUserService.findByUsername("johndoe");
        System.out.println("Получен по username: " + fetchedByUsername);

        // Получение пользователя по email
        CustomerUser fetchedByEmail = customerUserService.findByEmail("john@example.com");
        System.out.println("Получен по email: " + fetchedByEmail);

        // Получение пользователя по phoneNumber
        CustomerUser fetchedByPhoneNumber = customerUserService.findByPhoneNumber("+1234567890");
        System.out.println("Получен по phoneNumber: " + fetchedByPhoneNumber);

        // Обновление номера телефона
        customerUserService.updatePhoneNumber(userId, "+1987654321");
        System.out.println("Обновлен телефон: " + customerUserService.findById(userId).getPhoneNumber());

        // Обновление email
        customerUserService.updateEmail(userId, "jon.smith@example.com");
        System.out.println("Обновлен email: " + customerUserService.findById(userId).getEmail());

        // Обновление username
        customerUserService.updateUsername(userId, "jonny");
        System.out.println("Обновлено имя пользователя: " + customerUserService.findById(userId).getUsername());

        // Обновление пароля (хэш)
        String newHashPassword = "newHashedPassword";
        customerUserService.updateHashPassword(userId, newHashPassword);
        System.out.println("Обновлен хэш пароля");

        // Обновление даты последнего входа
        LocalDateTime now = LocalDateTime.now();
        customerUserService.updateLastLoginDate(userId, now);
        System.out.println("Обновлена дата последнего входа: " + customerUserService.findById(userId).getLastLoginDate());

        // замена всех ролей и полномочий
        customerUserService.updateRoles(userId, EnumSet.of(CustomerUserRole.ROLE_CUSTOMER_EXPERIMENTAL, CustomerUserRole.ROLE_CUSTOMER));
        customerUserService.updateAuthorities(userId, EnumSet.of(CustomerUserAuthority.CUSTOMER_ADD_TO_SHOPPING_CART, CustomerUserAuthority.CUSTOMER_CREATE_FAVORITE_PRODUCT, CustomerUserAuthority.CUSTOMER_CREATE_FAVORITE_PRODUCT));

        // Добавление роли и полномочий
        customerUserService.addRole(userId, CustomerUserRole.ROLE_CUSTOMER_EXPERIMENTAL);
        System.out.println("Добавлена роль ROLE_CUSTOMER_EXPERIMENTAL");

        customerUserService.addAuthority(userId, CustomerUserAuthority.CUSTOMER_UPDATE_REVIEW);
        System.out.println("Добавлено полномочие CUSTOMER_UPDATE_REVIEW");

        // Удаление роли и полномочий
        customerUserService.removeRole(userId, CustomerUserRole.ROLE_CUSTOMER_EXPERIMENTAL);
        System.out.println("Удалена роль ROLE_CUSTOMER_EXPERIMENTAL");

        customerUserService.removeAuthority(userId, CustomerUserAuthority.CUSTOMER_UPDATE_REVIEW);
        System.out.println("Удалено полномочие CUSTOMER_UPDATE_REVIEW");

        // Добавление роли и полномочий
        customerUserService.addRoles(userId, EnumSet.of(CustomerUserRole.ROLE_CUSTOMER_EXPERIMENTAL));
        System.out.println("Добавлена роль ROLE_CUSTOMER_EXPERIMENTAL");

        customerUserService.addAuthorities(userId, EnumSet.of(CustomerUserAuthority.CUSTOMER_UPDATE_REVIEW));
        System.out.println("Добавлено полномочие CUSTOMER_UPDATE_REVIEW");

        // Удаление роли и полномочий
        customerUserService.removeRoles(userId, EnumSet.of(CustomerUserRole.ROLE_CUSTOMER));
        System.out.println("Удалена роль ROLE_CUSTOMER");

        customerUserService.removeAuthorities(userId, EnumSet.of(CustomerUserAuthority.CUSTOMER_READ_PRODUCT));
        System.out.println("Удалено полномочие CUSTOMER_READ_PRODUCT");

        // Обновление статусов аккаунта
        customerUserService.updateAccountNonExpired(userId, false);
        System.out.println("Обновлено accountNonExpired: false");

        customerUserService.updateAccountNonLocked(userId, false);
        System.out.println("Обновлено accountNonLocked: false");

        customerUserService.updateCredentialsNonExpired(userId, false);
        System.out.println("Обновлено credentialsNonExpired: false");

        customerUserService.updateEnabled(userId, false);
        System.out.println("Обновлено enabled: false");

        // Удаление пользователя по ID
        customerUserService.deleteById(userId);
        System.out.println("Пользователь удален по ID");
    }
}
