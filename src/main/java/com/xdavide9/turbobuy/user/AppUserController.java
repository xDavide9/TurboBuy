package com.xdavide9.turbobuy.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @GetMapping(path = "id/{userId}")
    public AppUser getUserById(@PathVariable("userId") Integer userId) {
        return repository
                .findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id " + userId + " does NOT exist."
                ));
    }

    @GetMapping(path = "username/{username}")
    public AppUser getUserByUsername(@PathVariable("username") String username) {
        return repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User " + username + " NOT found."
                ));
    }
}
