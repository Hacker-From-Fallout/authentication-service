package com.ignat.chernyshov.auth.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.ignat.chernyshov.auth.domain.entities.CustomerUser;

public class CustomerUserSpecifications {
    public static Specification<CustomerUser> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"); 
    }

    public static Specification<CustomerUser> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%"); 
    }

    public static Specification<CustomerUser> hasUsername(String username) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("username")), "%" + username.toLowerCase() + "%"); 
    }

    public static Specification<CustomerUser> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%"); 
    }

    public static Specification<CustomerUser> hasPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) -> {
            String searchTerm = phoneNumber.trim();

            if (!searchTerm.startsWith("+")) {
                searchTerm = "+" + searchTerm;
            }

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("phoneNumber")),
                searchTerm.toLowerCase() + "%"
            );
        };
    }
}
