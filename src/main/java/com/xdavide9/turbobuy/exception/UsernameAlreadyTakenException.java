package com.xdavide9.turbobuy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UsernameAlreadyTakenException extends RuntimeException {

    private final boolean registering;

    public UsernameAlreadyTakenException(String message, boolean registering) {
        super(message);
        this.registering = registering;
    }

    public boolean isRegistering() {
        return registering;
    }
}
