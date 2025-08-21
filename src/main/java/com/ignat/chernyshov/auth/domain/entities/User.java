package com.ignat.chernyshov.auth.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class User {

    @NonNull
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @NonNull
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NonNull
    @Column(name = "phone_number", unique = true, nullable = false)
    private String phoneNumber;

    @NonNull
    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(name = "registration_date", nullable = false)
    private final LocalDateTime registrationDate = LocalDateTime.now();

    @Column(name = "last_login_date", nullable = false)
    private LocalDateTime lastLoginDate = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (lastLoginDate == null) {
            lastLoginDate = LocalDateTime.now();
        }
    }
}