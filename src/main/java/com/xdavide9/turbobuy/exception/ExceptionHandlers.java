package com.xdavide9.turbobuy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

import static com.xdavide9.turbobuy.security.Redirect.REGISTER_ERROR;
import static com.xdavide9.turbobuy.security.Redirect.USER_API;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public RedirectView handleUsernameAlreadyTakenException(Exception e) {
        log.error(e.getMessage());
        log.error("Redirecting to '{}'", REGISTER_ERROR.getUrl());
        return new RedirectView(REGISTER_ERROR.getUrl());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public RedirectView handleApiNotFoundException(Exception e) {
        log.error(e.getMessage());
        log.error("Redirecting to '{}'", USER_API.getUrl());
        return new RedirectView(USER_API.getUrl());
    }
}
