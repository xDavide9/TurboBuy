package com.xdavide9.turbobuy.auth;

import com.xdavide9.turbobuy.exception.UserNotFoundException;
import com.xdavide9.turbobuy.user.AppUser;
import com.xdavide9.turbobuy.user.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(@Param("username") String username) {
        AppUser appUser = repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User '" + username + "' not found."));
        log.info("User '{}' with role '{}' successfully logged in", appUser.getUsername(), appUser.getRole());
        return new AppUserDetails(appUser);
    }
}
