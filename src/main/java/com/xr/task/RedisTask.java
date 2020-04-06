package com.xr.task;

import com.xr.service.modules.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Order(value = 1)
//@Component
public class RedisTask implements CommandLineRunner {

    @Autowired
    RedisService redisService;

    @Override
    public void run(String... args) throws Exception {
        redisService.getTest();
    }
}
