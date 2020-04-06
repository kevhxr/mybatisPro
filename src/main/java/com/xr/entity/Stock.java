package com.xr.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Stock implements Serializable {
    private static final long serialVersionUID = 8362913190743896894L;
    private int stockId;
    private int stockNum;
    private String stockName;
    private BigDecimal stockPrice;

    /**
     * DefaultResultSetHandler
     * createResultObject
     * metaType.hasDefaultConstructor()
     * 要提供无参构造器
     * 不然必须全字段匹配
     */
    public Stock() {
    }

    public Stock(int stockNum, String stockName, BigDecimal stockPrice) {
        this.stockNum = stockNum;
        this.stockName = stockName;
        this.stockPrice = stockPrice;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String name) {
        this.stockName = name;
    }

    public BigDecimal getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(BigDecimal price) {
        this.stockPrice = price;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stockId=" + stockId +
                ", stockNum=" + stockNum +
                ", stockName='" + stockName + '\'' +
                ", stockPrice=" + stockPrice +
                '}';
    }
}
