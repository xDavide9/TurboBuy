package com.xdavide9.turbobuy.security;

public enum Redirect {

    HOME("/"),
    LOGIN("/login"),
    LOGIN_ERROR("/login?error"),
    LOGIN_LOGOUT("/login?logout"),
    LOGOUT("/logout"),
    REGISTER("/register"),
    REGISTER_ERROR("/register?error"),
    USER_API("/api/v1/users"),
    SALE_API("/api/v1/sales"),
    SALES("/sales"),
    SALES_ERROR("/sales?error"),
    SALES_TOO_MANY("/sales?too_many"),
    ACCOUNT("/account"),
    ACCOUNT_CHANGE_USERNAME("/account/username"),
    ACCOUNT_CHANGE_USERNAME_TAKEN("/account?taken"),
    ACCOUNT_CHANGE_USERNAME_NO_MATCH("/account?username"),
    ACCOUNT_CHANGE_PASSWORDS_NO_MATCH("/account?password");


    private final String url;

    Redirect(String url) {
        this.url = url;
    }

    public final String getUrl() {
        return url;
    }
}
