package com.tang.mq.order.domain;

import java.util.Date;
import lombok.Data;

/**
 * @Classname ${NAME}
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/29 11:18
 * @Created by ASUS
 */
@Data
public class Order {
    private Integer id;

    /**
    * 订单名称
    */
    private String name;

    /**
    * 下单时间
    */
    private Date orderCreatetime;

    /**
    * 订单状态 0 已经未支付 1已经支付 2已退单
    */
    private Integer orderState;

    /**
    * 订单价格
    */
    private Double orderMoney;

    /**
    * 订单ID
    */
    private String orderId;
}