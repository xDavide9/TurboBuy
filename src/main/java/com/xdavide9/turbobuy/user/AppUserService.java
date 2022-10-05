package com.xdavide9.turbobuy.user;

import com.xdavide9.turbobuy.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppUserService {

    private final AppUserRepository repository;

    public List<AppUser> getUsers() {
        return repository.findAll();
    }

    public AppUser getUserById(Integer userId) {
        return repository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id '" + userId + "' not found."
                ));
    }

    public AppUser getUserByUsername(String username) {
        return repository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        "User '" + username + "' not found."
                ));
    }
}
