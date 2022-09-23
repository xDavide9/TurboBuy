package com.xdavide9.turbobuy.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class AppUserController {

    private final AppUserRepository repository;

    public AppUserController(AppUserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<AppUser> getUsers() {
        return repository.findAll();
    }

    @GetMapping(path = "{userId}")
    public AppUser getUser(@PathVariable("userId") Integer userId) {
        return repository
                .findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                "User with id " + userId + " does NOT exist."
        ));
    }
}
