package com.xdavide9.turbobuy.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static com.xdavide9.turbobuy.security.ApplicationRole.*;

// These users currently can't be used to log in because database auth is not yet implemented
// They are saved in the db by a command line runner but in the future they will come from a form

@Configuration
public class UserConfigInMemory {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserConfigInMemory(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            List<User> userList = Arrays.asList(
              new User("john", encoder.encode("password123"), ADMIN.getGrantedAuthorities()),
              new User("janet", encoder.encode("password123"), USER.getGrantedAuthorities()),
              new User("marcus", encoder.encode("password123"), USER.getGrantedAuthorities())
            );
            repository.saveAll(userList);
        };
    }
}
