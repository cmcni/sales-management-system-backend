package com.cmcni.sales_management_system_backend.common.security;

import com.cmcni.sales_management_system_backend.domain.user.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class CustomUserDetails implements UserDetails {
    private String rememberToken;
    private User user;
    private Collection<SimpleGrantedAuthority> authorities;

    public CustomUserDetails(User user, String rememberToken) {
        this.user = user;
        this.rememberToken = rememberToken;
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getUserRoleType().getRoleType().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getEmailAddress();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
