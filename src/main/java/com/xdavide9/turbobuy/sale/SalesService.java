package com.xdavide9.turbobuy.sale;

import com.xdavide9.turbobuy.account.auth.AppUserDetails;
import com.xdavide9.turbobuy.exception.SaleAlreadyPresentException;
import com.xdavide9.turbobuy.user.AppUser;
import com.xdavide9.turbobuy.user.AppUserRepository;
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

    public RedirectView addNewSale(Sale sale, Authentication authentication, HttpServletRequest request) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        AppUser appUser = userDetails.getAppUser();
        if (saleRepository.findByTitle(sale.getTitle()).isPresent())
            throw new SaleAlreadyPresentException("Sale '" + sale.getTitle() + "' already present");
        sale.setAppUserName(appUser.getUsername());
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

    public List<Sale> getSales() {
        return saleRepository.findAll();
    }
}
