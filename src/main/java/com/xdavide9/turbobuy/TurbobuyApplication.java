package com.xdavide9.turbobuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TurbobuyApplication {

    // todo implement csrf
    // todo create redirect enum
    // todo create /error mapping

    public static void main(String[] args) {
        SpringApplication.run(TurbobuyApplication.class, args);
    }

}
