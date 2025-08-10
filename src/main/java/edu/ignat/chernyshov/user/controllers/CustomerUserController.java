package edu.ignat.chernyshov.user.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.ignat.chernyshov.user.domain.entities.CustomerUser;
import edu.ignat.chernyshov.user.services.CustomerUserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer-user/")
public class CustomerUserController {

    private final CustomerUserService customerUserService;

    @GetMapping("/users")
    public ResponseEntity<List<CustomerUser>> getUsers() {
        List<CustomerUser> users = customerUserService.findAll();

        return users.isEmpty() 
            ? ResponseEntity.noContent().build() 
            : ResponseEntity.ok(users);
    }
}
