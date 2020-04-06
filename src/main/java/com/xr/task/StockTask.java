package com.xr.task;

import com.xr.service.StockReconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StockTask implements CommandLineRunner {
    @Autowired
    StockReconService service;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i <1 ; i++) {
            service.getStockData(1);
        }
        for (int i = 0; i <1 ; i++) {
            service.getStockData(1);
        }
    }
}
