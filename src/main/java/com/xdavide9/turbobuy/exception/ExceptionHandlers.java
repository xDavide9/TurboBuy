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
        log.error(e.getMessage());
        String redirect;
        if (e.isRegistering()) {
            redirect = REGISTER_ERROR.getUrl();
        } else {
            redirect = ACCOUNT_CHANGE_USERNAME_TAKEN.getUrl();
        }
        log.error("Redirecting to '{}'", redirect);
        return new RedirectView(redirect);
    }

    @ExceptionHandler(UsernamesDoNotMatchException.class)
    public RedirectView handleUsernamesDoNotMatchException(UsernamesDoNotMatchException e) {
        log.error(e.getMessage());
        log.error("Redirecting to '{}'", ACCOUNT_CHANGE_USERNAME_NO_MATCH.getUrl());
        return new RedirectView(ACCOUNT_CHANGE_USERNAME_NO_MATCH.getUrl());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public RedirectView handleUserNotFoundException(Exception e) {
        log.error(e.getMessage());
        log.error("Redirecting to '{}'", USER_API.getUrl());
        return new RedirectView(USER_API.getUrl());
    }

    @ExceptionHandler(SaleNotFoundException.class)
    public RedirectView handleSaleNotFoundException(Exception e) {
        log.error(e.getMessage());
        log.error("Redirecting to '{}'", SALE_API.getUrl());
        return new RedirectView(SALE_API.getUrl());
    }
}
