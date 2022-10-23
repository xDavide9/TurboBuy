package com.xdavide9.turbobuy.sales;

import com.xdavide9.turbobuy.sale.Sale;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SalesController {
    private final SalesService salesService;

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    public RedirectView addNewSale(Sale sale, Authentication authentication, HttpServletRequest request) {
        return salesService.addNewSale(sale, authentication, request);
    }
}
