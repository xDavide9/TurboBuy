package com.xdavide9.turbobuy.account;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(
            path = "/username",
            consumes = "application/json"
    )
    public void changeUsername(Authentication authentication, String currentUsername, String newUsername) {
        accountService.changeUsername(authentication, currentUsername, newUsername);
    }
}
