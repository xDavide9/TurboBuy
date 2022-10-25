package com.xdavide9.turbobuy.account;

import com.xdavide9.turbobuy.auth.AppUserDetails;
import com.xdavide9.turbobuy.exception.UsernameAlreadyTakenException;
import com.xdavide9.turbobuy.exception.UsernamesDoNotMatchException;
import com.xdavide9.turbobuy.user.AppUser;
import com.xdavide9.turbobuy.user.AppUserRepository;
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

    public RedirectView changeUsername(UsernameChange usernameChange, Authentication authentication, HttpServletRequest request) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        AppUser appUser = userDetails.getAppUser();
        String inputCurrentUsername = usernameChange.getCurrentUsername();
        String actualCurrentUsername = appUser.getUsername();
        String newUsername = usernameChange.getNewUsername();
        if (!(inputCurrentUsername.equals(actualCurrentUsername)))
            throw new UsernamesDoNotMatchException(
                    "input '" + inputCurrentUsername +
                            "' does not match '" + actualCurrentUsername + "'");
        if (appUserRepository.findByUsername(newUsername).isPresent())
            throw new UsernameAlreadyTakenException("Username '" + newUsername + "' already present", false);
        Set<UsernameChange> usernameChanges = appUser.getUsernameChanges();
        usernameChanges.add(usernameChange);
        appUser.setUsernameChanges(usernameChanges);
        appUser.setUsername(newUsername);
        appUserRepository.save(appUser);
        log.info(
                "User '" + inputCurrentUsername +
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

    public RedirectView changePassword(PasswordChange passwordChange, Authentication authentication, HttpServletRequest request) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        AppUser appUser = userDetails.getAppUser();
        // todo add validation
        appUser.setPassword(encoder.encode(passwordChange.newPassword()));
        appUserRepository.save(appUser);
        try {
            request.logout();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        log.info("User '" + appUser.getUsername() + "' changed his password");
        return new RedirectView(HOME.getUrl());
    }
}
