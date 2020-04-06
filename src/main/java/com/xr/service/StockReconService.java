package com.xr.service;

import com.xr.entity.Stock;
import com.xr.service.modules.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockReconService {

    @Autowired
    StockService stockService;

    public void getStockData(int stockId){
        List<Stock> stockData = stockService.getStockData(stockId);
        stockData.stream().forEach(stock-> System.out.println(stock));
    }
}
