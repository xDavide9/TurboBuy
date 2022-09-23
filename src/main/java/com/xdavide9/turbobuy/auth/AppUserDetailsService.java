package com.xdavide9.turbobuy.auth;

import com.xdavide9.turbobuy.user.AppUser;
import com.xdavide9.turbobuy.user.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository repository;

    public AppUserDetailsService(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByUsername(username);
        return new AppUserDetails(
                appUser.getUsername(),
                appUser.getPassword(),
                appUser.getGrantedAuthorities(),
                appUser.isAccountNonExpired(),
                appUser.isAccountNonLocked(),
                appUser.isCredentialsNonExpired(),
                appUser.isEnabled()
            );
    }
}
