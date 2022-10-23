package com.xdavide9.turbobuy.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsernameChangeRepository extends JpaRepository<UsernameChange, Integer> {
}
