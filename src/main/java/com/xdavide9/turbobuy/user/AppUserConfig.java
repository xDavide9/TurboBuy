package com.xdavide9.turbobuy.user;

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
    CommandLineRunner commandLineRunner() {
        return args -> {
            List<AppUser> appUsers = List.of(
                    new AppUser("anna", encoder.encode("password"), ADMIN.getGrantedAuthorities()),
                    new AppUser("marcus", encoder.encode("password")),
                    new AppUser("andrew", encoder.encode("password"), ADMIN.getGrantedAuthorities())
            );
            repository.saveAll(appUsers);
        };
    }
}
