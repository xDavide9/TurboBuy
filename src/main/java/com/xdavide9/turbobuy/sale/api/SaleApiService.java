package com.xdavide9.turbobuy.sale.api;

import com.xdavide9.turbobuy.exception.SaleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleApiService {
    private final SaleRepository repository;

    public List<Sale> getSales() {
        return repository.findAll();
    }

    public Sale getSaleById(Integer saleId) {
        return repository
                .findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException(
                        "Sale with saleId '" + saleId + "' not found."
                ));
    }

    public List<Sale> getSalesByAppUserId(Integer appUserId) {
        // todo debug exception is never thrown
        return repository
                .findByAppUserId(appUserId)
                .orElseThrow(() -> new SaleNotFoundException(
                        "Sales of user with appUserId '" + appUserId + "' not found."
                ));
    }
}
