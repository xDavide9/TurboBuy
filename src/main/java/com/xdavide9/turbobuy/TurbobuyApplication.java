package com.xdavide9.turbobuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TurbobuyApplication {

    // todo implement csrf
    // todo handle cases such as when you are logged in and you should log out before going to /register,
    //  generally test the flow of the application

    public static void main(String[] args) {
        SpringApplication.run(TurbobuyApplication.class, args);
    }

}
