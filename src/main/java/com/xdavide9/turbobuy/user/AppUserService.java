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

    public AppUser getUserById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id '" + id + "' not found."
                ));
    }

    public AppUser getUserByUsername(String username) {
        return repository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        "User '" + username + "' not found."
                ));
    }

    public List<Sale> getSalesByUserId(Integer id) {
        return getUserById(id).getSales();
    }

    public List<Sale> getSalesByUserUsername(String username) {
        return getUserByUsername(username).getSales();
    }
}
