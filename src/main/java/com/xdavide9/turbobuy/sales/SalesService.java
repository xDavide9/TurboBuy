package com.xdavide9.turbobuy.sales;

import com.xdavide9.turbobuy.auth.AppUserDetails;
import com.xdavide9.turbobuy.sale.Sale;
import com.xdavide9.turbobuy.user.AppUser;
import com.xdavide9.turbobuy.user.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Set;

import static com.xdavide9.turbobuy.security.Redirect.HOME;

@Service
@AllArgsConstructor
@Slf4j
public class SalesService {

    private final AppUserRepository appUserRepository;

    public RedirectView addNewSale(Sale sale, Authentication authentication) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        AppUser appUser = userDetails.getAppUser();
        Set<Sale> sales = appUser.getSales();
        sales.add(sale);
        appUser.setSales(sales);
        appUserRepository.save(appUser);
        log.info("User '" + appUser + "' Successfully posted sale '" + sale + "'");
        return new RedirectView(HOME.getUrl());
    }
}
