package com.xdavide9.turbobuy.account;

import com.xdavide9.turbobuy.auth.AppUserDetails;
import com.xdavide9.turbobuy.user.AppUser;
import com.xdavide9.turbobuy.user.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private final AppUserRepository repository;

    public void changeUsername(Authentication authentication, String currentUsername, String newUsername) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        AppUser user = userDetails.getAppUser();
        if (!(currentUsername.equals(user.getUsername())))
            throw new RuntimeException("no match"); // improve later
        if (repository.findByUsername(newUsername).isPresent())
            throw new RuntimeException("already taken"); // improve later
        user.setUsername(newUsername);
        repository.save(user);
    }
}
