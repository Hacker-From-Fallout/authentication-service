package com.ignat.chernyshov.auth.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.ignat.chernyshov.auth.domain.entities.SellerUser;

public class SellerUserSpecifications {

    public static Specification<SellerUser> hasUsername(String username) {
        return (root, query, criteriaBuilder) -> {
            String searchPattern = username.toLowerCase() + "%";

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("username")),
                searchPattern
            );
        };
    }

    public static Specification<SellerUser> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            String searchPattern = email.toLowerCase() + "%";

            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("email")),
                searchPattern
            );
        };
    }

    public static Specification<SellerUser> hasPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) -> {
            String searchTerm = phoneNumber.trim();

            if (!searchTerm.startsWith("+")) {
                searchTerm = "+" + searchTerm;
            }

            String searchPattern = searchTerm.toLowerCase() + "%";
            return criteriaBuilder.like(
                criteriaBuilder.lower(root.get("phoneNumber")),
                searchPattern
            );
        };
    }
}
