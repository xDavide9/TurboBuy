package com.xdavide9.turbobuy.security;

import com.xdavide9.turbobuy.user.account.auth.AppUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.xdavide9.turbobuy.security.Redirect.*;
import static com.xdavide9.turbobuy.user.api.AppUserRole.ADMIN;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final AppUserDetailsService appUserDetailsService;
    private final PasswordEncoder encoder;
    private final LoginSuccessHandler loginSuccessHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HOME.getUrl()).permitAll()
                .antMatchers("/api/**").hasRole(ADMIN.name())
                .antMatchers(REGISTER.getUrl(), LOGIN.getUrl()).not().authenticated()
                .antMatchers(SALES.getUrl(), ACCOUNT.getUrl()).authenticated()
                .and()
                .formLogin()
                .loginPage(LOGIN.getUrl())
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler)
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
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
