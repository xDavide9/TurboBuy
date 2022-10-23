package com.xdavide9.turbobuy.account;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(
            path = "/username",
            consumes = "application/json"
    )
    public RedirectView changeUsername(@RequestBody UsernameChange usernameChange, Authentication authentication) {
        return accountService.changeUsername(usernameChange, authentication);
    }
}
