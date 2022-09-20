package com.xdavide9.turbobuy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserConfig {

    private final PasswordEncoder encoder;

    public UserConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    public UserDetailsService users() {
        UserDetails anna = User.builder()
                .username("anna")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails muzio = User.builder()
                .username("muzio")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();
        UserDetails reginaldo = User.builder()
                .username("reginaldo")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(anna, muzio, reginaldo);
    }
}
