package com.tang.mq.stock.domain;

import lombok.Data;

/**
 * @Classname ${NAME}
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/28 22:41
 * @Created by ASUS
 */
@Data
public class Stock {
    private Integer id;

    /**
    * 订单ID
    */
    private String orderId;

    /**
    * 库存余额
    */
    private Integer stock;
}