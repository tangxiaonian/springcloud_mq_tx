package com.tang.mq.stock.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname DirectMqConfig
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/28 21:50
 * @Created by ASUS
 */
@Configuration
public class DirectMqConfig {

    // 消息 direct 方式
    private final static String MQ_ORDER_QUEUE = "mq.create_order.queue";
    private final static String MQ_STOCK_QUEUE = "mq.stock.queue";

    private final static String MQ_STOCK_ORDER_EXCHANGE = "mq.stock.exchange";

    private final static String MQ_STOCK_ORDER__ROUTINGKEY = "mq.stock.create_order";

    @Bean
    public Queue orderDirectQueue() {
        return new Queue(MQ_ORDER_QUEUE, true, false, false);
    }

    @Bean
    public Queue stockDirectQueue() {
        return new Queue(MQ_STOCK_QUEUE, true, false, false);
    }

    @Bean
    public DirectExchange orderStockDirectExchange() {
        return new DirectExchange(MQ_STOCK_ORDER_EXCHANGE, true, false);
    }

    @Bean
    public Binding orderBindMessageExchange(Queue orderDirectQueue, DirectExchange orderStockDirectExchange) {

        return BindingBuilder.bind(orderDirectQueue).to(orderStockDirectExchange).with(MQ_STOCK_ORDER__ROUTINGKEY);

    }

    @Bean
    public Binding stockBindMessageExchange(Queue stockDirectQueue, DirectExchange orderStockDirectExchange) {

        return BindingBuilder.bind(stockDirectQueue).to(orderStockDirectExchange).with(MQ_STOCK_ORDER__ROUTINGKEY);

    }

}