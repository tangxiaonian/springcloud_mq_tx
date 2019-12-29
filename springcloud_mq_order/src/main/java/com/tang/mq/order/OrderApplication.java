package com.tang.mq.order;

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
@MapperScan(value = {"com.tang.mq.order.mapper"})
@SpringBootApplication
@EnableTransactionManagement
public class OrderApplication {

    public static void main(String[] args) {

        SpringApplication.run(OrderApplication.class, args);


    }

}