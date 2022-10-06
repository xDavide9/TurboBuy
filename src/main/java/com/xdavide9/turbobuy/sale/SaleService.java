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

    public Sale getSaleById(Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new SaleNotFoundException(
                        "Sale with id '" + id + "' not found."
                ));
    }
}
