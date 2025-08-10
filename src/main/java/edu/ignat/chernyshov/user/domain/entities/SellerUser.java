package edu.ignat.chernyshov.user.domain.entities;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import edu.ignat.chernyshov.user.domain.authorities.SellerUserAuthority;
import edu.ignat.chernyshov.user.domain.authorities.SellerUserRole;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Table(name = "seller_users")
public class SellerUser extends User implements UserDetails {

    @NonNull
    @Column(name = "organization_id", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long organizationId;

    @NonNull
    @ElementCollection(targetClass = SellerUserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "seller_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    private Set<SellerUserRole> roles;

    @NonNull
    @ElementCollection(targetClass = SellerUserAuthority.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "seller_user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "authorities", nullable = false)
    private Set<SellerUserAuthority> authorities;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = this.authorities.stream()
                    .map(SellerUserAuthority::name)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
    
        authorities.addAll(this.roles.stream()
                    .map(SellerUserRole::name)
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
