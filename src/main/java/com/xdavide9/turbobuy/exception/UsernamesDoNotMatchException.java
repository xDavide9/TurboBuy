package com.xdavide9.turbobuy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UsernamesDoNotMatchException extends RuntimeException {
    public UsernamesDoNotMatchException(String message) {
        super(message);
    }
}
