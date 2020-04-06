package com.xr.task;

import com.xr.entity.Stock;
import com.xr.service.modules.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//@Component
public class JDBCTask implements CommandLineRunner {

    @Autowired
    StockService stockDaoExecutor;

    @Override
    public void run(String... args) throws Exception {
        List<Stock> stocks = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 800; i++) {
            double b = random.nextInt(10) + 0.1;
            BigDecimal price = new BigDecimal(
                        random.nextInt(100) / b);
            stocks.add(new Stock(i * 100, "stock_" + i, price));
        }

        stockDaoExecutor.batchInsert(stocks);
    }
}
