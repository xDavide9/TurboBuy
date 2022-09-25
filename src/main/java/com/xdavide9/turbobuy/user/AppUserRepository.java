package com.xdavide9.turbobuy.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    @Query(
            value = "SELECT * FROM app_user u WHERE u.username = :username",
            nativeQuery = true
    )
    Optional<AppUser> findByUsername(String username);
}
