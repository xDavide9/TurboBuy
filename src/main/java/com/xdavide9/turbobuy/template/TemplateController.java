package com.xdavide9.turbobuy.template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping
    public String getHomeView() {
        return "home";
    }

    @GetMapping(path = "login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping(path = "register")
    public String getRegisterView() {
        return "register";
    }

    @GetMapping(path = "sales")
    public String getSalesView() {
        return "sales";
    }
}
