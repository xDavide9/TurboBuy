package com.xdavide9.turbobuy.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {
    @Query(
            value = "SELECT * FROM sale u WHERE u.app_user_id = :appUserId",
            nativeQuery = true
    )
    Optional<List<Sale>> findByAppUserId(Integer appUserId);
}
