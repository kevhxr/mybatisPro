package com.xr;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.xr.mapper")
public class MyBaticMain {

    static {
        System.setProperty("mode", "redis");
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(MyBaticMain.class, args);
        run.close();
    }
}
