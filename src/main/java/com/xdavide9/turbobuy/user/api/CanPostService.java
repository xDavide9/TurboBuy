package com.xdavide9.turbobuy.user.api;


import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

// makes can post variable true for every user each 24 hours

@Service
public class CanPostService {

    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final AppUserRepository userRepository;

    public CanPostService(AppUserRepository repository) {
        userRepository = repository;
        scheduler.scheduleAtFixedRate(() -> {
            userRepository
                    .findAll()
                    .forEach(user -> user.setCanPost(true));
        }, 0, 1, TimeUnit.DAYS);
    }

}
