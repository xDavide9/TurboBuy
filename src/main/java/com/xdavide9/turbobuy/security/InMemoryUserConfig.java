package com.xdavide9.turbobuy.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.xdavide9.turbobuy.security.AppUserRole.*;

@Configuration
public class InMemoryUserConfig {

    private final PasswordEncoder encoder;

    public InMemoryUserConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    public UserDetailsService users() {
        UserDetails anna = User.builder()
                .username("anna")
                .password(encoder.encode("password"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        UserDetails muzio = User.builder()
                .username("muzio")
                .password(encoder.encode("password"))
                .authorities(USER.getGrantedAuthorities())
                .build();
        UserDetails reginaldo = User.builder()
                .username("reginaldo")
                .password(encoder.encode("password"))
                .authorities(USER.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(anna, muzio, reginaldo);
    }
}
