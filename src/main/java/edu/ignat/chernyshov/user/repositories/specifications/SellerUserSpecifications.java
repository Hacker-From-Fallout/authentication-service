package edu.ignat.chernyshov.user.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import edu.ignat.chernyshov.user.domain.entities.SellerUser;


public class SellerUserSpecifications {
    public static Specification<SellerUser> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%"); 
    }

    public static Specification<SellerUser> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%"); 
    }

    public static Specification<SellerUser> hasUsername(String username) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("username")), "%" + username.toLowerCase() + "%"); 
    }

    public static Specification<SellerUser> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> 
            criteriaBuilder.like(
                criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%"); 
    }

    public static Specification<SellerUser> hasPhoneNumber(String phoneNumber) {
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
