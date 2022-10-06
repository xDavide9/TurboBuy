package com.xdavide9.turbobuy.sale;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SaleConfig {
    private final SaleRepository repository;

    @Bean
    CommandLineRunner saleCommandLineRunner() {
        return args -> {
            Sale sale = new Sale("MacBook air m1", "Beautiful pc");
            repository.save(sale);
        };
    }
}
