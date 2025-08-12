package edu.ignat.chernyshov.user.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.ignat.chernyshov.user.domain.entities.RootUser;

@Repository
public interface RootUserRepository extends JpaRepository<RootUser, Long> {
    List<RootUser> findAll();
    Optional<RootUser> findById(Long id);
    Optional<RootUser> findByEmail(String email);
    Optional<RootUser> findByPhoneNumber(String phoneNumber);
    Optional<RootUser> findByUsername(String username);

    @Modifying
    @Query(value = "UPDATE RootUser u SET u.firstName = :firstName WHERE u.id = :id")
    void updateFirstName(@Param("id") Long id, @Param("firstName") String firstName);

    @Modifying
    @Query(value = "UPDATE RootUser u SET u.lastName = :lastName WHERE u.id = :id")
    void updateLastName(@Param("id") Long id, @Param("lastName") String lastName);

    @Modifying
    @Query(value = "UPDATE RootUser u SET u.phoneNumber = :phoneNumber WHERE u.id = :id")
    void updatePhoneNumber(@Param("id") Long id, @Param("phoneNumber") String phoneNumber);

    @Modifying
    @Query(value = "UPDATE RootUser u SET u.email = :email WHERE u.id = :id")
    void updateEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying
    @Query(value = "UPDATE RootUser u SET u.username = :username WHERE u.id = :id")
    void updateUsername(@Param("id") Long id, @Param("username") String username);

    @Modifying
    @Query(value = "UPDATE RootUser u SET u.hashPassword = :hashPassword WHERE u.id = :id")
    void updateHashPassword(@Param("id") Long id, @Param("hashPassword") String hashPassword);

    @Modifying
    @Query(value = "UPDATE RootUser u SET u.lastLoginDate = :lastLoginDate WHERE u.id = :id")
    void updateLastLoginDate(@Param("id") Long id, @Param("lastLoginDate") LocalDateTime lastLoginDate);

    @Modifying
    @Query(value = "UPDATE RootUser u SET u.accountNonExpired = :accountNonExpired WHERE u.id = :id")
    void updateAccountNonExpired(@Param("id") Long id, @Param("accountNonExpired") boolean accountNonExpired);

    @Modifying
    @Query(value = "UPDATE RootUser u SET u.accountNonLocked = :accountNonLocked WHERE u.id = :id")
    void updateAccountNonLocked(@Param("id") Long id, @Param("accountNonLocked") boolean accountNonLocked);

    @Modifying
    @Query(value = "UPDATE RootUser u SET u.credentialsNonExpired = :credentialsNonExpired WHERE u.id = :id")
    void updateCredentialsNonExpired(@Param("id") Long id, @Param("credentialsNonExpired") boolean credentialsNonExpired);

    @Modifying
    @Query(value = "UPDATE RootUser u SET u.enabled = :enabled WHERE u.id = :id")
    void updateEnabled(@Param("id") Long id, @Param("enabled") boolean valenabledue);

    @Modifying
    @Query(value = "MERGE INTO root_user_authorities (user_id, authorities) KEY (user_id, authorities) VALUES (:userId, :authority)", nativeQuery = true)
    void addAuthority(@Param("userId") Long userId, @Param("authority") String authority);

    @Modifying
    @Query(value = "DELETE FROM root_user_authorities WHERE user_id = :userId AND authorities = :authority", nativeQuery = true)
    void removeAuthority(@Param("userId") Long userId, @Param("authority") String authority);

    @Modifying
    @Query(value = "MERGE INTO root_user_roles (user_id, roles) KEY (user_id, roles) VALUES (:userId, :role)", nativeQuery = true)
    void addRole(@Param("userId") Long userId, @Param("role") String role);

    @Modifying
    @Query(value = "DELETE FROM root_user_roles WHERE user_id = :userId AND roles = :role", nativeQuery = true)
    void removeRole(@Param("userId") Long userId, @Param("role") String role);

    void deleteById(@Param("id") Long id);

    boolean existsById(Long Id);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUsername(String username);
}
