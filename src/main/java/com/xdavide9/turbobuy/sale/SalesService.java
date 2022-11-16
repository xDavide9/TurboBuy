package com.xdavide9.turbobuy.sale;

import com.xdavide9.turbobuy.exception.SaleAlreadyPresentException;
import com.xdavide9.turbobuy.sale.api.Sale;
import com.xdavide9.turbobuy.sale.api.SaleRepository;
import com.xdavide9.turbobuy.user.account.auth.AppUserDetails;
import com.xdavide9.turbobuy.user.api.AppUser;
import com.xdavide9.turbobuy.user.api.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

import static com.xdavide9.turbobuy.security.Redirect.HOME;

@Service
@AllArgsConstructor
@Slf4j
public class SalesService {

    private final AppUserRepository appUserRepository;
    private final SaleRepository saleRepository;

    public RedirectView addNewSale(String title,
                                   String description,
                                   Authentication authentication,
                                   HttpServletRequest request) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        AppUser appUser = userDetails.getAppUser();
        if (saleRepository.findByTitle(title).isPresent())
            throw new SaleAlreadyPresentException("Sale '" + title + "' already present");
        Sale sale = new Sale(title, description, appUser.getUsername());
        Set<Sale> sales = appUser.getSales();
        sales.add(sale);
        appUser.setSales(sales);
        appUserRepository.save(appUser);
        log.info("User '" + appUser.getUsername() + "' Successfully posted sale '" + sale + "'");
        // logging user out to apply changes
        try {
            request.logout();
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return new RedirectView(HOME.getUrl());
    }

    // todo change this so that it's random
    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    public List<Sale> getSalesByAppUserId(Integer appUserId) {
        return saleRepository.findByAppUserId(appUserId)
                .orElseThrow(() -> new IllegalStateException("Error while fetching data"));
    }
}
