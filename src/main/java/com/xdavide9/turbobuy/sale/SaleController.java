package com.xdavide9.turbobuy.sale;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/sales")
@AllArgsConstructor
public class SaleController {
    private final SaleService saleService;

    @GetMapping
    public List<Sale> getSales() {
        return saleService.getSales();
    }

    @GetMapping("/id/{saleId}")
    public Sale getSaleById(@PathVariable("saleId") Integer saleId) {
        return saleService.getSaleById(saleId);
    }
}
