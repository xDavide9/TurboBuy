package com.xdavide9.turbobuy.user.account.auth;

import com.xdavide9.turbobuy.exception.UserNotFoundException;
import com.xdavide9.turbobuy.user.api.AppUser;
import com.xdavide9.turbobuy.user.api.AppUserRepository;
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
        return new AppUserDetails(appUser);
    }
}
