package com.tang.mq.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Classname OrderApplication
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/23 19:09
 * @Created by ASUS
 */
@EnableTransactionManagement
@SpringBootApplication
@MapperScan(value = {"com.tang.mq.stock.mapper"})
public class StockApplication {

    public static void main(String[] args) {

        SpringApplication.run(StockApplication.class, args);

    }

}