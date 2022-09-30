package com.xdavide9.turbobuy.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler(UsernameAlreadyTakenException.class)
    public RedirectView handleUsernameAlreadyTakenException(Exception e) {
        System.out.println(e.getMessage());
        return new RedirectView("/register?error");
    }

    @ExceptionHandler(NotFoundException.class)
    public void handleNotFoundException(Exception e) {
        System.out.println(e.getMessage());
    }
}
