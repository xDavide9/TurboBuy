package com.xdavide9.turbobuy.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@Configuration
public class AddDefaultUsersConfiguration {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public AddDefaultUsersConfiguration(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            List<User> userList = Arrays.asList(
              new User("anna", encoder.encode("password")),
              new User("muzio", encoder.encode("password")),
              new User("reginaldo", encoder.encode("password"))
            );
            repository.saveAll(userList);
        };
    }
}
