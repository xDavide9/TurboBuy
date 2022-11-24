package com.xdavide9.turbobuy.sale;

import com.xdavide9.turbobuy.sale.api.Sale;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/sales")
@AllArgsConstructor
@Controller
public class SalesController {
    private final SalesService salesService;

    @Deprecated
    // @PostMapping
    public RedirectView addNewSale(@RequestParam("title") String title,
                                   @RequestParam("description") String description,
                                   Authentication authentication,
                                   HttpServletRequest request) {
        return salesService.addNewSale(title, description, authentication, request);
    }

    public void addNewSaleV2(Sale sale, Authentication authentication) {
        salesService.addNewSaleV2(sale, authentication);
    }

    public List<Sale> getSales() {
        return salesService.getSales();
    }

    public List<Sale> getSalesByAppUserId(Integer appUserId) {
        return salesService.getSalesByAppUserId(appUserId);
    }
}
