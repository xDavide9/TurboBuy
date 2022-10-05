package com.xdavide9.turbobuy.registration;

import com.xdavide9.turbobuy.user.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public RedirectView register(AppUser user) {
        return registrationService.register(user);
    }
}
