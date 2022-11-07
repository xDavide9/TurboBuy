package com.xdavide9.turbobuy.user.account;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(path = "/username")
    public RedirectView changeUsername(@RequestParam("currentUsername") String currentUsername,
                                       @RequestParam("newUsername") String newUsername,
                                       Authentication authentication,
                                       HttpServletRequest request) {
        return accountService.changeUsername(currentUsername, newUsername, authentication, request);
    }

    @PostMapping(path = "password")
    public RedirectView changePassword(@RequestParam("currentPassword") String currentPassword,
                                       @RequestParam("newPassword") String newPassword,
                                       Authentication authentication,
                                       HttpServletRequest request) {
        return accountService.changePassword(currentPassword, newPassword, authentication, request);
    }
}
