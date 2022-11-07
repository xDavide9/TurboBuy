package com.xdavide9.turbobuy.sale;

import com.xdavide9.turbobuy.sale.api.Sale;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/sales")
@AllArgsConstructor
public class SalesController {
    private final SalesService salesService;

    @PostMapping
    public RedirectView addNewSale(@RequestParam("title") String title,
                                   @RequestParam("description") String description,
                                   Authentication authentication,
                                   HttpServletRequest request) {
        return salesService.addNewSale(title, description, authentication, request);
    }

    public List<Sale> getSales() {
        return salesService.getSales();
    }
}
