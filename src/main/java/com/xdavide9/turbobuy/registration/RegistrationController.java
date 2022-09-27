package com.xdavide9.turbobuy.registration;

import com.xdavide9.turbobuy.user.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(consumes = {"application/json"})
    public void register(@RequestBody AppUser user) {
        registrationService.register(user);
    }
}
