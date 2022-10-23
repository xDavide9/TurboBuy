package com.xdavide9.turbobuy.account;

import com.xdavide9.turbobuy.auth.AppUserDetails;
import com.xdavide9.turbobuy.user.AppUser;
import com.xdavide9.turbobuy.user.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Set;

import static com.xdavide9.turbobuy.security.Redirect.HOME;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService {

    private final AppUserRepository repository;

    public RedirectView changeUsername(UsernameChange usernameChange, Authentication authentication) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        AppUser appUser = userDetails.getAppUser();
        Set<UsernameChange> usernameChanges = appUser.getUsernameChanges();
        usernameChanges.add(usernameChange);
        appUser.setUsernameChanges(usernameChanges);
        appUser.setUsername(usernameChange.getNewUsername());
        repository.save(appUser);
        log.info(
                "User '" + usernameChange.getCurrentUsername() +
                        "' changed his username to '" + usernameChange.getNewUsername() + "'"
        );
        return new RedirectView(HOME.getUrl());
    }
}
