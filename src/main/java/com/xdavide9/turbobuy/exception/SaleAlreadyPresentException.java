package com.xdavide9.turbobuy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class SaleAlreadyPresentException extends RuntimeException {
    public SaleAlreadyPresentException(String message) {
        super(message);
    }
}
