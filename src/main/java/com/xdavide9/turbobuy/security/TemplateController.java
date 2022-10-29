package com.xdavide9.turbobuy.security;

import com.xdavide9.turbobuy.account.auth.AppUserDetails;
import com.xdavide9.turbobuy.sale.SalesController;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class TemplateController {

    private SalesController salesController;

    @GetMapping(path = "account")
    public String getAccountView(Model model, Authentication authentication) {
        model.addAttribute(
                "currentUser",
                ((AppUserDetails) authentication.getPrincipal()).getAppUser()
        );
        return "account";
    }

    @GetMapping(path = "sales")
    public String getSalesView(Model model) {
        model.addAttribute(
                "sales",
                salesController.getSales()
        );
        return "sales";
    }

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

    @GetMapping(path = "contact")
    public String getContactView() {
        return "contact";
    }
}
