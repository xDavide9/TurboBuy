package com.xdavide9.turbobuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TurbobuyApplication {

    // todo implement csrf
    // todo create footer
    // todo create my sales section, home section, contact section
    // todo add image support to the application
    // todo make sale cards show an image uploaded by the user and when hovered show details
    // todo make sale cards "closeable"; show a random limited number of results from db; add button to reload for new results
    // todo work on theming
    public static void main(String[] args) {
        SpringApplication.run(TurbobuyApplication.class, args);
    }

}
