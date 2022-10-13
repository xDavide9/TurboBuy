package com.xdavide9.turbobuy.onsale;

import com.xdavide9.turbobuy.auth.AppUserDetails;
import com.xdavide9.turbobuy.sale.Sale;
import com.xdavide9.turbobuy.user.AppUser;
import com.xdavide9.turbobuy.user.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

import static com.xdavide9.turbobuy.security.Redirect.HOME;

@Service
@AllArgsConstructor
public class OnSaleService {

    private final AppUserRepository appUserRepository;

    public RedirectView addNewSale(Sale sale, Authentication authentication) {
        AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
        AppUser appUser = userDetails.getAppUser();
        List<Sale> sales = appUser.getSales();
        sales.add(sale);
        appUser.setSales(sales);
        appUserRepository.save(appUser);
        return new RedirectView(HOME.getUrl());
    }
}
