package com.xdavide9.turbobuy.auth;

import com.xdavide9.turbobuy.user.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Slf4j
public class AppUserDetails implements UserDetails {

    private final AppUser appUser;

    public AppUserDetails(AppUser appUser) {
        this.appUser = appUser;
        log.info("Successfully logged in user '{}'", appUser.getUsername());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appUser.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return appUser.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return appUser.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return appUser.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return appUser.isEnabled();
    }

    public AppUser getAppUser() {
        return appUser;
    }
}
