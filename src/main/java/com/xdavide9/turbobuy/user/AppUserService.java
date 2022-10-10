package com.xdavide9.turbobuy.user;

import com.xdavide9.turbobuy.exception.UserNotFoundException;
import com.xdavide9.turbobuy.sale.Sale;
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

    public AppUser getUserById(Integer appUserId) {
        return repository
                .findById(appUserId)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with appUserId '" + appUserId + "' not found."
                ));
    }

    public AppUser getUserByUsername(String username) {
        return repository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        "User '" + username + "' not found."
                ));
    }

    public List<Sale> getSalesByUserId(Integer appUserId) {
        return getUserById(appUserId).getSales();
    }

    public List<Sale> getSalesByUserUsername(String username) {
        return getUserByUsername(username).getSales();
    }
}
