package com.xdavide9.turbobuy.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository repository;

    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
    }

    public List<AppUser> getUsers() {
        return repository.findAll();
    }

    public AppUser getUserById(Integer userId) {
        return repository
                .findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "User with id " + userId + " does NOT exist."
                ));
    }

    public AppUser getUserByUsername(String username) {
        return repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User " + username + " NOT found."
                ));
    }
}
