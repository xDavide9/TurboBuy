package com.xdavide9.turbobuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TurbobuyApplication {

    // todo implement csrf
    // todo implement /register view with a form that perform a post request to its same url
    // todo create error mapping

    public static void main(String[] args) {
        SpringApplication.run(TurbobuyApplication.class, args);
    }

}
