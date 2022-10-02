package com.xdavide9.turbobuy.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
@Slf4j
public class ExceptionHandlers {
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public RedirectView handleUsernameAlreadyTakenException(Exception e) {
        log.error(e.getMessage());
        log.error("Redirecting to '/register?error'");
        return new RedirectView("/register?error");
    }

    @ExceptionHandler(NotFoundException.class)
    public RedirectView handleNotFoundException(Exception e) {
        log.error(e.getMessage());
        log.error("Redirecting to '/api/v1/users'");
        return new RedirectView("/api/v1/users");
    }
}
