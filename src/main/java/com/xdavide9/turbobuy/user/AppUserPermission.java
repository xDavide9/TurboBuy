package com.xdavide9.turbobuy.user;

public enum AppUserPermission {
    SALES_READ("sales:read"),
    SALES_WRITE("sales:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permissionString;

    AppUserPermission(String permissionString) {
        this.permissionString = permissionString;
    }

    public String getPermissionString() {
        return permissionString;
    }
}
