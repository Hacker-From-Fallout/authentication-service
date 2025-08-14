package com.ignat.chernyshov.auth.domain.entities;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ignat.chernyshov.auth.domain.authorities.CustomerUserAuthority;
import com.ignat.chernyshov.auth.domain.authorities.CustomerUserRole;

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
@Table(name = "customer_users")
public class CustomerUser extends User implements UserDetails {

    @NonNull
    @ElementCollection(targetClass = CustomerUserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable = false)
    private Set<CustomerUserRole> roles;

    @NonNull
    @ElementCollection(targetClass = CustomerUserAuthority.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_user_authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "authorities", nullable = false)
    private Set<CustomerUserAuthority> authorities;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @JsonIgnore
    public Set<CustomerUserAuthority> getAuthoritiesSet() {
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = this.authorities.stream()
                    .map(CustomerUserAuthority::name)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

        authorities.addAll(this.roles.stream()
                    .map(CustomerUserRole::name)
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

    public String toString() {
        System.out.println(super.getId());
        System.out.println(super.getFirstName());
        System.out.println(super.getLastName());
        System.out.println(super.getPhoneNumber());
        System.out.println(super.getEmail());
        System.out.println(super.getUsername());
        System.out.println(super.getHashPassword());
        System.out.println(super.getLastLoginDate());
        System.out.println(super.getRegistrationDate());
        System.out.println(accountNonExpired);
        System.out.println(accountNonLocked);
        System.out.println(credentialsNonExpired);
        System.out.println(enabled);
        System.out.println(getRoles().toString());
        System.out.println(getAuthorities().toString());
        return "";
    }
}
