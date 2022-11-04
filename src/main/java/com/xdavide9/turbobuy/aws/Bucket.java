package com.xdavide9.turbobuy.aws;

public enum Bucket {
    ProfilePicturesBucket("turbobuyprofilepictures"),
    SalesPictureBucket("turbobuysalespictures");
    private final String name;

    Bucket(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
