package com.xdavide9.turbobuy.exception;

public class TooManyFormSubmissionsException extends RuntimeException {
    public TooManyFormSubmissionsException(String message) {
        super(message);
    }
}
