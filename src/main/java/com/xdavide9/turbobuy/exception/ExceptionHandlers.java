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
        String redirect;
        if (e.isRegistering())
            redirect = REGISTER_ERROR.getUrl();
        else
            redirect = ACCOUNT_CHANGE_USERNAME_TAKEN.getUrl();
        log.error("Redirecting to '{}'", redirect);
        return new RedirectView(redirect);
    }

    @ExceptionHandler(UsernamesDoNotMatchException.class)
    public RedirectView handleUsernamesDoNotMatchException(UsernamesDoNotMatchException e) {
        log.error("UsernamesDoNotMatchException", e);
        String redirect = ACCOUNT_CHANGE_USERNAME_NO_MATCH.getUrl();
        log.error("Redirecting to '{}'", redirect);
        return new RedirectView(redirect);
    }

    @ExceptionHandler(PasswordsDoNotMatchException.class)
    public RedirectView handlePasswordsDoNotMatchException(PasswordsDoNotMatchException e) {
        log.error("PassWordsDoNotMatchException", e);
        String redirect = ACCOUNT_CHANGE_PASSWORDS_NO_MATCH.getUrl();
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

    @ExceptionHandler(TooManyFormSubmissionsException.class)
    public RedirectView handleTooManyFormSubmissionsException(TooManyFormSubmissionsException e) {
        log.error("TooManyFormSubmissionsException", e);
        String redirect = SALES_TOO_MANY.getUrl();
        log.error("Redirecting to '{}'", redirect);
        return new RedirectView(redirect);
    }
}
