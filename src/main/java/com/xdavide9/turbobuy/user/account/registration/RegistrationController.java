package com.xdavide9.turbobuy.user.account.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping()
    public RedirectView register(HttpServletRequest request,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password) {
        return registrationService.register(request, username, password);
    }
}
