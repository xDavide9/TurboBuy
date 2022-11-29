package com.xdavide9.turbobuy.user.api;

import com.xdavide9.turbobuy.sale.api.Sale;
import com.xdavide9.turbobuy.user.account.UsernameChange;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static com.xdavide9.turbobuy.user.api.AppUserRole.ADMIN;


@Configuration
@AllArgsConstructor
public class AppUserConfig {

    private final PasswordEncoder encoder;
    private final AppUserRepository repository;

    @Bean
    CommandLineRunner appUserCommandLineRunner() {
        return args -> {
            AppUser admin = new AppUser(
                    "admin",
                    encoder.encode("admin"),
                    ADMIN,
                    ADMIN.getGrantedAuthorities()
            );

            AppUser user = new AppUser(
                    "user",
                    encoder.encode("user")
            );
            Set<Sale> sales = user.getSales();
            sales.add(new Sale(
                    "Macbook air m1",
                    "Very fast personal computer, useful for school or work. Has the m1 processor.",
                    "user"
            ));
            sales.add(new Sale(
                    "Pan",
                    "Cook eggs or meat in this pan, resists to very high temperatures",
                    "user"
            ));
            Set<UsernameChange> usernameChanges = user.getUsernameChanges();
            usernameChanges.add(new UsernameChange(
                    "current",
                    "user"
            ));

            AppUser user2 = new AppUser(
                    "user2",
                    encoder.encode("user2")
            );
            Set<Sale> sales2 = user2.getSales();
            sales2.add(new Sale(
                    "Tesla Model X",
                    "Very fast car made by Tesla (Elon's company). It's electric and is good for the environment.",
                    "user2"
            ));
            repository.save(admin);
            repository.save(user);
            repository.save(user2);
        };
    }
}
