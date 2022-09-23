package com.xdavide9.turbobuy.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static com.xdavide9.turbobuy.security.AppUserRole.ADMIN;

@Configuration
public class AppUserConfig {

    private final PasswordEncoder encoder;
    private final AppUserRepository repository;

    public AppUserConfig(PasswordEncoder encoder, AppUserRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            List<AppUser> appUsers = List.of(
                    new AppUser("anna", encoder.encode("password"), ADMIN.getGrantedAuthorities()),
                    new AppUser("marcus", encoder.encode("password"))
            );
            repository.saveAll(appUsers);
        };
    }
}
