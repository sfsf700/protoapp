package com.prototype.protoapp.stock.entity;

import com.prototype.protoapp.stock.repository.LoginUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class LoginUserDetails implements UserDetails {
    private final LoginUser loginUser;
    private final Collection<? extends GrantedAuthority> authorities;

    public LoginUserDetails(LoginUser loginUser) {
        this.loginUser = loginUser;
        this.authorities = loginUser.rolelist()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
    public LoginUser getLoginUser() {
        return loginUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    @Override
    public String getPassword() {
        return loginUser.password();
    }

    @Override
    public String getUsername() {
        return loginUser.email();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
