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
                    "Beautifulaaaaaaaaaaaadklfajsdklfjdalkfjlakfjalfjsklafjdkaslfjdksalfjsalfjsalfjdaslfjalfjladfk" +
                            "asjkdfjklajfdlakjfaldfjad",
                    "user"
            ));
            sales.add(new Sale(
                    "Used Toaster",
                    "bruh",
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
                    "Awesome",
                    "user2"
            ));
            repository.save(admin);
            repository.save(user);
            repository.save(user2);
        };
    }
}
