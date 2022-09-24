package com.xdavide9.turbobuy.security;

import com.xdavide9.turbobuy.auth.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.xdavide9.turbobuy.security.AppUserRole.ADMIN;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AppUserDetailsService appUserDetailsService;
    private final PasswordEncoder encoder;

    public SecurityConfig(AppUserDetailsService appUserDetailsService, PasswordEncoder encoder) {
        this.appUserDetailsService = appUserDetailsService;
        this.encoder = encoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "index").permitAll()
                .antMatchers("/api/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password");
        return http.build();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserDetailsService);
        provider.setPasswordEncoder(encoder);
        return provider;
    }
}