package com.xdavide9.turbobuy.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class AppUserController {

    private final AppUserService appUserService;

    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping
    public List<AppUser> getUsers() {
        return appUserService.getUsers();
    }

    @GetMapping(path = "id/{userId}")
    public AppUser getUserById(@PathVariable("userId") Integer userId) {
        return appUserService.getUserById(userId);
    }

    @GetMapping(path = "username/{username}")
    public AppUser getUserByUsername(@PathVariable("username") String username) {
        return appUserService.getUserByUsername(username);
    }
}
