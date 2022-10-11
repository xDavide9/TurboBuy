package com.xdavide9.turbobuy.user;

import com.xdavide9.turbobuy.sale.Sale;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping
    public List<AppUser> getUsers() {
        return appUserService.getUsers();
    }

    @GetMapping(path = "/id/{appUserId}")
    public AppUser getUserById(@PathVariable("appUserId") Integer appUserId) {
        return appUserService.getUserById(appUserId);
    }

    @GetMapping(path = "/username/{username}")
    public AppUser getUserByUsername(@PathVariable("username") String username) {
        return appUserService.getUserByUsername(username);
    }

    @GetMapping(path = "/appUserId/{appUserId}/sales")
    public List<Sale> getSalesByUserId(@PathVariable("appUserId") Integer appUserId) {
        return appUserService.getSalesByUserId(appUserId);
    }

    @GetMapping(path = "/username/{username}/sales")
    public List<Sale> getSalesByUserUsername(@PathVariable("username") String username) {
        return appUserService.getSalesByUserUsername(username);
    }
}
