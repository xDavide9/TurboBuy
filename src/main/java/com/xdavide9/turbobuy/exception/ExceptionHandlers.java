package com.xdavide9.turbobuy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import static com.xdavide9.turbobuy.security.Redirect.*;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public RedirectView handleUsernameAlreadyTakenException(UsernameAlreadyTakenException e) {
        log.error("UsernameAlreadyTakenException", e);
        String redirect = REGISTER_ERROR.getUrl();
        log.error("Redirecting to '{}'", redirect);
        return new RedirectView(redirect);
    }

    @ExceptionHandler(SaleAlreadyPresentException.class)
    public RedirectView handleSaleAlreadyPresentException(SaleAlreadyPresentException e) {
        log.error("SaleAlreadyPresentException", e);
        String redirect = SALES_ERROR.getUrl();
        log.error("Redirecting to '{}'", redirect);
        return new RedirectView(redirect);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public RedirectView handleUserNotFoundException(UserNotFoundException e) {
        log.error("UserNotFoundException", e);
        String redirect = USER_API.getUrl();
        log.error("Redirecting to '{}'", redirect);
        return new RedirectView(redirect);
    }

    @ExceptionHandler(SaleNotFoundException.class)
    public RedirectView handleSaleNotFoundException(SaleNotFoundException e) {
        log.error("SaleNotFoundException", e);
        String redirect = SALE_API.getUrl();
        log.error("Redirecting to '{}'", redirect);
        return new RedirectView(redirect);
    }
}
