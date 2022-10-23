package com.xdavide9.turbobuy.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UsernameAlreadyTakenException extends RuntimeException {

    @Getter
    private final boolean registering;

    public UsernameAlreadyTakenException(String message, boolean registering) {
        super(message);
        this.registering = registering;
    }
}
