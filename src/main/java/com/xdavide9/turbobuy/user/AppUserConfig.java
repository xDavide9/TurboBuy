package com.xdavide9.turbobuy.user;

import com.xdavide9.turbobuy.sale.Sale;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static com.xdavide9.turbobuy.user.AppUserRole.ADMIN;


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
            List<Sale> sales = user.getSales();
            sales.add(new Sale(
                    "Macbook air m1",
                    "Beautiful"
            ));
            sales.add(new Sale(
                    "Used Toaster",
                    "bruh"
            ));

            AppUser user2 = new AppUser(
                    "user2",
                    encoder.encode("user2")
            );
            List<Sale> sales2 = user2.getSales();
            sales2.add(new Sale(
                    "Tesla Model X",
                    "Awesome"
            ));

            repository.save(admin);
            repository.save(user);
            repository.save(user2);
        };
    }
}
