package com.xdavide9.turbobuy.sale;

import com.xdavide9.turbobuy.exception.SaleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleService {
    private final SaleRepository repository;

    public List<Sale> getSales() {
        return repository.findAll();
    }

    public Sale getSaleById(Integer saleId) {
        return repository
                .findById(saleId)
                .orElseThrow(() -> new SaleNotFoundException(
                        "Sale with id '" + saleId + "' not found."
                ));
    }
}
