package com.xdavide9.turbobuy.sale.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/sales")
@AllArgsConstructor
public class SaleApiController {
    private final SaleApiService saleApiService;

    @GetMapping
    public List<Sale> getSales() {
        return saleApiService.getSales();
    }

    @GetMapping("/id/{saleId}")
    public Sale getSaleById(@PathVariable("saleId") Integer saleId) {
        return saleApiService.getSaleById(saleId);
    }

    @GetMapping("/user/id/{appUserId}")
    public List<Sale> getSalesByAppUserId(@PathVariable("appUserId") Integer appUserId) {
        return saleApiService.getSalesByAppUserId(appUserId);
    }
}
