package com.xdavide9.turbobuy.account;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(
            path = "/username",
            consumes = "application/x-www-form-urlencoded"
    )
    public RedirectView changeUsername(UsernameChange usernameChange, Authentication authentication, HttpServletRequest request) {
        return accountService.changeUsername(usernameChange, authentication, request);
    }

    @PostMapping(
            path = "password",
            consumes = "application/x-www-form-urlencoded"
    )
    public RedirectView changePassword(PasswordChange passwordChange, Authentication authentication, HttpServletRequest request) {
        return accountService.changePassword(passwordChange, authentication, request);
    }
}
