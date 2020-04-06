package com.xr.task;

import com.xr.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*
@Component
@Order(value = 2)
*/

public class CacheTestTask implements CommandLineRunner {

    @Autowired
    CacheService cacheService;

    @Override
    public void run(String... args) throws Exception {
        //cacheService.queryWithDiffSession();
        cacheService.cacheRefreshWhole();
    }
}
