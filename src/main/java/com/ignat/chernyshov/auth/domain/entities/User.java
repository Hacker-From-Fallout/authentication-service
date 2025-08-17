package com.ignat.chernyshov.auth.domain.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sequence")
    @SequenceGenerator(name = "user_id_sequence", sequenceName = "user_id_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

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