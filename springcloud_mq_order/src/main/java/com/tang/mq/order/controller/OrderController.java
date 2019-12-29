package com.tang.mq.order.controller;


import com.tang.mq.order.domain.Order;
import com.tang.mq.order.service.OrderServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @Classname OrderController
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/23 19:15
 * @Created by ASUS
 */
@RestController
public class OrderController {

    @Resource
    private OrderServiceImpl orderService;

    @GetMapping("/order")
    public String order(Integer i) {

        Order order = new Order();

        order.setName("蚂蚁课堂");
        order.setOrderCreatetime(new Date());
        order.setOrderState(1);
        order.setOrderMoney(10.0);
        order.setOrderId(UUID.randomUUID().toString().replace("-",""));

        orderService.add(order,i);

        return "success";
    }

}