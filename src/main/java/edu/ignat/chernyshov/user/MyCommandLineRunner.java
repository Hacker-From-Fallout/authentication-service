package edu.ignat.chernyshov.user;

import java.time.LocalDateTime;
import java.util.EnumSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import edu.ignat.chernyshov.user.domain.authorities.CustomerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.CustomerUserRole;
import edu.ignat.chernyshov.user.domain.entities.CustomerUser;
import edu.ignat.chernyshov.user.services.CustomerUserServiceImpl;

// разобраться с ролями и их добавлением и обновлением

@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final CustomerUserServiceImpl customerUserService;

    public MyCommandLineRunner(CustomerUserServiceImpl customerUserService) {
        this.customerUserService = customerUserService;
    }

    @Override
    public void run(String... args) throws Exception {

        CustomerUser customerUser = customerUserService.createUser(EnumSet.of(CustomerUserRole.ROLE_CUSTOMER), EnumSet.of(CustomerUserAuthority.CUSTOMER_ADD_TO_SHOPPING_CART),"Ignat", "Chernushov", "username123", "email@ttx", "10745221498", "24532fwas");

        System.out.println(customerUser.toString());

        customerUserService.updateFirstName(Long.valueOf(1), "IgnatUpdate");
        customerUserService.removeAuthority(Long.valueOf(1), CustomerUserAuthority.CUSTOMER_MAKE_AN_ORDER.name());

        System.out.println(customerUserService.findById(Long.valueOf(1)).toString()); 
        
        customerUserService.addAuthority(Long.valueOf(1), CustomerUserAuthority.CUSTOMER_MAKE_AN_ORDER.name());
        System.out.println(customerUserService.findById(Long.valueOf(1)).toString());

        customerUserService.updateUser(Long.valueOf(1), EnumSet.of(CustomerUserRole.ROLE_CUSTOMER), EnumSet.of(CustomerUserAuthority.CUSTOMER_CREATE_REVIEW),"Ignat", "Chernushov", "username123", "email@ttx", "10745221498", "24532fwas", null);

        System.out.println(customerUserService.findById(Long.valueOf(1)).toString());

        //System.out.println(customerUserService.findById(Long.valueOf(214)));
    }
}
