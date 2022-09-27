package com.xdavide9.turbobuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TurbobuyApplication {

    // todo implement csrf
    // todo implement custom exception handling and passing to frontend
    // todo implement /register view with a form that perform a post request to its same url

    public static void main(String[] args) {
        SpringApplication.run(TurbobuyApplication.class, args);
    }

}
