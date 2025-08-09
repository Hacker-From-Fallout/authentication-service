package edu.ignat.chernyshov.user.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;
import edu.ignat.chernyshov.user.domain.entities.RootUser;

@Repository
public interface RootUserRepository extends JpaRepository<RootUser, Long> {
    List<RootUser> findAll();
    Optional<RootUser> findById(Long id);
    Optional<RootUser> findByEmail(String email);
    Optional<RootUser> findByPhoneNumber(String phoneNumber);
    Optional<RootUser> findByUsername(String username);

    @Modifying
    @Query("UPDATE RootUser u SET u.firstName = :firstName WHERE u.id = :id")
    void updateFirstName(@Param("id") Long id, @Param("firstName") String firstName);

    @Modifying
    @Query("UPDATE RootUser u SET u.lastName = :lastName WHERE u.id = :id")
    void updateLastName(@Param("id") Long id, @Param("lastName") String lastName);

    @Modifying
    @Query("UPDATE RootUser u SET u.phoneNumber = :phoneNumber WHERE u.id = :id")
    void updatePhoneNumber(@Param("id") Long id, @Param("phoneNumber") String phoneNumber);

    @Modifying
    @Query("UPDATE RootUser u SET u.email = :email WHERE u.id = :id")
    void updateEmail(@Param("id") Long id, @Param("email") String email);

    @Modifying
    @Query("UPDATE RootUser u SET u.username = :username WHERE u.id = :id")
    void updateUsername(@Param("id") Long id, @Param("username") String username);

    @Modifying
    @Query("UPDATE RootUser u SET u.hashPassword = :hashPassword WHERE u.id = :id")
    void updateHashPassword(@Param("id") Long id, @Param("hashPassword") String hashPassword);

    @Modifying
    @Query("UPDATE RootUser u SET u.lastLoginDate = :lastLoginDate WHERE u.id = :id")
    void updateLastLoginDate(@Param("id") Long id, @Param("lastLoginDate") LocalDateTime lastLoginDate);

    @Modifying
    @Query("UPDATE RootUser u SET u.authorities = :authorities WHERE u.id = :id")
    void updateAuthorities(@Param("id") Long id, @Param("authorities") Set<RootUserAuthority> authorities);

    @Modifying
    @Query(value = "INSERT INTO root_user_authorities (user_id, authorities) VALUES (:userId, :authority)", nativeQuery = true)
    void addAuthority(@Param("userId") Long userId, @Param("authority") String authority);

    @Modifying
    @Query(value = "INSERT INTO root_user_authorities (user_id, authorities) VALUES (:userId, :authority)", nativeQuery = true)
    void addAuthorities(@Param("userId") Long userId, @Param("authorities") List<String> authorities);

    @Modifying
    @Query(value = "DELETE FROM root_user_authorities WHERE user_id = :userId AND authorities = :authority", nativeQuery = true)
    void removeAuthority(@Param("userId") Long userId, @Param("authority") String authority);
    
    void deleteById(Long id);

    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByUsername(String username);
}
