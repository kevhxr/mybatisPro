package com.xr.task;

import com.xr.service.modules.TransFrontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

//@Component
public class TransactionTask implements CommandLineRunner {

    @Autowired
    TransFrontService service;

    @Override
    public void run(String... args) {
        service.callRequiredNew();
    }
}
