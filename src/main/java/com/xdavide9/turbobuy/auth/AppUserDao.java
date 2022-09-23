package com.xdavide9.turbobuy.auth;

import java.util.Optional;

public interface AppUserDao {

    Optional<AppUserDetails> selectApplicationUserByUsername(String username);
}
