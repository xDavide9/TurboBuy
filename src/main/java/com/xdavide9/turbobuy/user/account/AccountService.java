package com.xdavide9.turbobuy.user.account;

import com.xdavide9.turbobuy.exception.PasswordsDoNotMatchException;
import com.xdavide9.turbobuy.exception.UsernameAlreadyTakenException;
import com.xdavide9.turbobuy.exception.UsernamesDoNotMatchException;
import com.xdavide9.turbobuy.user.account.auth.AppUserDetails;
import com.xdavide9.turbobuy.user.api.AppUser;
import com.xdavide9.turbobuy.user.api.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;

import static com.xdavide9.turbobuy.security.Redirect.HOME;

@Service
@AllArgsConstructor
@Slf4j
public class AccountService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder encoder;

    public RedirectView changeUsername(String currentUsername,
                                       String newUsername,
                                       Authentication authentication,
                                       HttpServletRequest request) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        AppUser appUser = userDetails.getAppUser();
        String actualCurrentUsername = appUser.getUsername();
        if (!(currentUsername.equals(actualCurrentUsername)))
            throw new UsernamesDoNotMatchException(
                    "input '" + currentUsername +
                            "' does not match '" + actualCurrentUsername + "'");
        if (appUserRepository.findByUsername(newUsername).isPresent())
            throw new UsernameAlreadyTakenException("Username '" + newUsername + "' already present", false);
        UsernameChange usernameChange = new UsernameChange(currentUsername, newUsername);
        Set<UsernameChange> usernameChanges = appUser.getUsernameChanges();
        usernameChanges.add(usernameChange);
        appUser.setUsernameChanges(usernameChanges);
        appUser.setUsername(newUsername);
        appUserRepository.save(appUser);
        log.info(
                "User '" + currentUsername +
                        "' changed his username to '" + newUsername + "'"
        );
        // logging user out to apply changes
        try {
            request.logout();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return new RedirectView(HOME.getUrl());
    }

    public RedirectView changePassword(String currentPassword,
                                       String newPassword,
                                       Authentication authentication,
                                       HttpServletRequest request) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        AppUser appUser = userDetails.getAppUser();
        String actualCurrentPassword = appUser.getPassword();
        if (!(encoder.matches(currentPassword, actualCurrentPassword)))
            throw new PasswordsDoNotMatchException("Input password does not match actual password");
        appUser.setPassword(encoder.encode(newPassword));
        appUserRepository.save(appUser);
        // logging user out to apply changes
        try {
            request.logout();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        log.info("User '" + appUser.getUsername() + "' changed his password");
        return new RedirectView(HOME.getUrl());
    }
}
