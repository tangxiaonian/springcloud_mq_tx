package com.tang.mq.order.service;

import com.tang.mq.order.domain.Order;
import com.tang.mq.order.mapper.OrderMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Classname OrderService
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/24 17:00
 * @Created by ASUS
 */
@Service
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class OrderServiceImpl implements RabbitTemplate.ConfirmCallback{

    private final static String MQ_STOCK_ORDER_EXCHANGE = "mq.stock.exchange";

    private final static String MQ_STOCK_ORDER__ROUTINGKEY = "mq.stock.create_order";

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private OrderMapper orderMapper;

    @Transactional(rollbackFor = {RuntimeException.class})
    public void add(Order order, Integer i) {

        int result = orderMapper.insert(order);

        if (result > 0) {
            // 插入成功 异步发送消息 通知库存减少
            send(order.getOrderId());
        }
        int a = 1 / i;
    }

    private void send(String orderId) {

        // 封装消息
        Message message = MessageBuilder.withBody(orderId.getBytes())
                .setMessageId(orderId)
                .setContentType(MessageProperties.CONTENT_TYPE_BYTES)
                .build();

        // 构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(orderId);

        // 注册回调函数
        this.rabbitTemplate.setConfirmCallback(this);
        this.rabbitTemplate.setMandatory(true);

        // 发送消息
        rabbitTemplate.convertAndSend(MQ_STOCK_ORDER_EXCHANGE,MQ_STOCK_ORDER__ROUTINGKEY,message,correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        //id 都是相同的
        String orderId_ = correlationData.getId();

        if (ack) {
            System.out.println("Confirm...消息发送成功....");
            return;
        }
        // 发送失败 ，进行重试发送
        send(orderId_);

        System.out.println("Confirm...消息发送失败...进行重新发送...");

    }
}