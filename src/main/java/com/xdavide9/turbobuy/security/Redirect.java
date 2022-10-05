package com.xdavide9.turbobuy.security;

public enum Redirect {

    HOME("/"),
    LOGIN("/login"),
    LOGIN_ERROR("/login?error"),
    LOGIN_LOGOUT("/login?logout"),
    LOGOUT("/logout"),
    REGISTER("/register"),
    REGISTER_ERROR("/register?error"),
    USER_API("/api/v1/users");

    private final String url;

    Redirect(String url) {
        this.url = url;
    }

    public final String getUrl() {
        return url;
    }
}
