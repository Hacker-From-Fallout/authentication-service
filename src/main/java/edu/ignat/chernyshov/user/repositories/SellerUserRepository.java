package edu.ignat.chernyshov.user.repositories;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.ignat.chernyshov.user.domain.entities.SellerUser;

@Repository
public interface SellerUserRepository extends JpaRepository<SellerUser, Long>, JpaSpecificationExecutor<SellerUser> {
    Optional<SellerUser> findById(Long id);
    Optional<SellerUser> findByUsername(String username);
    Optional<SellerUser> findByEmail(String email);
    Optional<SellerUser> findByPhoneNumber(String phoneNumber);

    @Modifying
    @Query(value = "UPDATE seller_users u SET first_name = :firstName WHERE u.user_id = :id", nativeQuery = true)
    void updateFirstName(@Param("id") Long id, @Param("firstName") String firstName);

    @Modifying
    @Query(value = "UPDATE seller_users u SET last_name = :lastName WHERE u.user_id = :id", nativeQuery = true)
    void updateLastName(@Param("id") Long id, @Param("lastName") String lastName);

    @Modifying
    @Query(value = "UPDATE seller_users u SET username = :username WHERE u.user_id = :id", nativeQuery = true)
    void updateUsername(@Param("id") Long id, @Param("username") String username);

    @Modifying
    @Query(value = "UPDATE seller_users u SET email = :email WHERE u.user_id = :id", nativeQuery = true)
    void updateEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying
    @Query(value = "UPDATE seller_users u SET phone_number = :phoneNumber WHERE u.user_id = :id", nativeQuery = true)
    void updatePhoneNumber(@Param("id") Long id, @Param("phoneNumber") String phoneNumber);

    @Modifying
    @Query(value = "UPDATE seller_users u SET hash_password = :hashPassword WHERE u.user_id = :id", nativeQuery = true)
    void updateHashPassword(@Param("id") Long id, @Param("hashPassword") String hashPassword);

    @Modifying
    @Query(value = "UPDATE seller_users u SET last_login_date = :lastLoginDate WHERE u.user_id= :id", nativeQuery = true)
    void updateLastLoginDate(@Param("id") Long id, @Param("lastLoginDate") LocalDateTime lastLoginDate);

    @Modifying
    @Query(value = "UPDATE seller_users u SET account_non_expired= :accountNonExpired WHERE u.user_id= :id", nativeQuery = true)
    void updateAccountNonExpired(@Param("id") Long id, @Param("accountNonExpired") boolean accountNonExpired);

    @Modifying
    @Query(value = "UPDATE seller_users u SET account_non_locked= :accountNonLocked WHERE u.user_id= :id", nativeQuery = true)
    void updateAccountNonLocked(@Param("id") Long id, @Param("accountNonLocked") boolean accountNonLocked);

    @Modifying
    @Query(value = "UPDATE seller_users u SET credentials_non_expired= :credentialsNonExpired WHERE u.user_id= :id", nativeQuery = true)
    void updateCredentialsNonExpired(@Param("id") Long id, @Param("credentialsNonExpired") boolean credentialsNonExpired);

    @Modifying
    @Query(value = "UPDATE seller_users u SET enabled= :enabled WHERE u.user_id= :id", nativeQuery = true)
    void updateEnabled(@Param("id") Long id, @Param("enabled") boolean enabled);

    @Modifying
    @Query(value = 
        "INSERT INTO seller_user_authorities (user_id, authorities) VALUES (:userId, :authority) " +
        "ON CONFLICT (user_id, authorities) DO NOTHING", nativeQuery = true)
    void addAuthority(@Param("userId") Long userId, @Param("authority") String authority);

    @Modifying
    @Query(value = "DELETE FROM seller_user_authorities WHERE user_id= :userId AND authorities= :authority", nativeQuery = true)
    void removeAuthority(@Param("userId") Long userId, @Param("authority") String authority);

    @Modifying
    @Query(value = "DELETE FROM seller_user_authorities WHERE user_id= :userId", nativeQuery = true)
    void deleteAllAuthorities(@Param("userId") Long userId);

    @Modifying
    @Query(value = 
        "INSERT INTO seller_user_roles (user_id, roles) VALUES (:userId, :role) " +
        "ON CONFLICT (user_id, roles) DO NOTHING", nativeQuery = true)
    void addRole(@Param("userId") Long userId, @Param("role") String role);

    @Modifying
    @Query(value = "DELETE FROM seller_user_roles WHERE user_id= :userId AND roles= :role", nativeQuery = true)
    void removeRole(@Param("userId") Long userId, @Param("role") String role);

    @Modifying
    @Query(value = "DELETE FROM seller_user_roles WHERE user_id= :userId", nativeQuery = true)
    void deleteAllRoles(@Param("userId") Long userId);

    void deleteById(Long id);

    boolean existsById(Long Id);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUsername(String username);
}
