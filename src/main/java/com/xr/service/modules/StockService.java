package com.xr.service.modules;

import com.xr.entity.Stock;
import com.xr.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
public class StockService extends NamedParameterJdbcDaoSupport {

    private final String BATCHINSERTUSER =
            "insert into stock (stock_num,stock_name,stock_price) " +
                    "values (:stockNum,:stockName,:stockPrice)";

    @Autowired
    @Qualifier("myDataSource")
    DataSource dataSource;

    @Autowired
    UserMapper mapper;

    @Autowired
    public void init(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    public void batchInsertLoop(List<Stock> stockList) {
        long startTime = System.currentTimeMillis();
        stockList.forEach(stock -> {
            this.getNamedParameterJdbcTemplate()
                    .update(BATCHINSERTUSER,
                            new BeanPropertySqlParameterSource(stock));
        });

        long duration = System.currentTimeMillis() - startTime;
        System.out.println("takes: " + duration);
    }


    public void batchInsert(List<Stock> stockList) {
        long startTime = System.currentTimeMillis();
        this.getNamedParameterJdbcTemplate()
                .batchUpdate(BATCHINSERTUSER,
                        SqlParameterSourceUtils.createBatch(stockList.toArray()));
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("takes: " + duration);
    }


    @Cacheable(value = "myCache", key = "('stock_').concat(T(String).valueOf(#stockId))")
    public List<Stock> getStockData(int stockId) {
        logger.info("Not Hit! go to DB");
        return mapper.findStockById(stockId);
    }

}
