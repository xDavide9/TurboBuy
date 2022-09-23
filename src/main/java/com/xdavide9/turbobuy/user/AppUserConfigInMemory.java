package com.xdavide9.turbobuy.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static com.xdavide9.turbobuy.security.AppUserRole.*;

// These users currently can't be used to log in because database auth is not yet implemented
// They are saved in the db by a command line runner but in the future they will come from a form

@Configuration
public class AppUserConfigInMemory {

    private final AppUserRepository repository;
    private final PasswordEncoder encoder;

    public AppUserConfigInMemory(AppUserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            List<AppUser> appUserList = Arrays.asList(
              new AppUser("john", encoder.encode("password123"), ADMIN.getGrantedAuthorities()),
              new AppUser("janet", encoder.encode("password123"), USER.getGrantedAuthorities()),
              new AppUser("marcus", encoder.encode("password123"), USER.getGrantedAuthorities())
            );
            repository.saveAll(appUserList);
        };
    }
}
