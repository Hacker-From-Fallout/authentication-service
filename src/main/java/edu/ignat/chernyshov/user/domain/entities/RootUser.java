package edu.ignat.chernyshov.user.domain.entities;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.ignat.chernyshov.user.domain.authorities.RootUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.RootUserRole;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Table(name = "root_users")
public class RootUser extends User implements UserDetails {

    @NonNull
    @ElementCollection(targetClass = RootUserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "root_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    private Set<RootUserRole> roles;

    @NonNull
    @ElementCollection(targetClass = RootUserAuthority.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "root_user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "authorities", nullable = false)
    private Set<RootUserAuthority> authorities;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @JsonIgnore
    public Set<RootUserAuthority> getAuthoritiesSet() {
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = this.authorities.stream()
                    .map(RootUserAuthority::name)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

        authorities.addAll(this.roles.stream()
                    .map(RootUserRole::name)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList()));
    
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getHashPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
