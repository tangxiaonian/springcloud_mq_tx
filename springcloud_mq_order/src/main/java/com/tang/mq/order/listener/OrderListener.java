package com.tang.mq.order.listener;

import com.rabbitmq.client.Channel;
import com.tang.mq.order.domain.Order;
import com.tang.mq.order.mapper.OrderMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * @Classname OrderListener
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/28 21:49
 * @Created by ASUS
 */
@Component
public class OrderListener {

    @Resource
    private OrderMapper orderMapper;

    /**
     * MethodName receive
     * Description [ 补单队列 ]
     * Date 2019/12/28 22:59
     * Param [message, headers, channel]
     * return
     **/
    @RabbitListener(queues = {"mq.create_order.queue"})
    public void receive(Message message, @Headers Map<String,Object> headers, Channel channel) throws IOException {

        String orderId = new String(message.getBody());

        // 根据orderId判断 生产者是否插入成功
        Order order = orderMapper.selectByOrderId(orderId);

        Optional<Order> optionalOrder = Optional.ofNullable(order);

        Long deliveryTag  = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);

        // order 为 null 订单生成失败，重新尝试插入
        if (!optionalOrder.isPresent()) {

            System.out.println( "order数据异常，重新尝试插入...." );

            Order order_ = new Order();

            order_.setName("蚂蚁课堂");
            order_.setOrderCreatetime(new Date());
            order_.setOrderState(1);
            order_.setOrderMoney(10.0);
            order_.setOrderId(orderId);

            int value = orderMapper.insert(order_);

            if (value > 0) {

                System.out.println( "开始签收order数据...." + deliveryTag );

                try {

                    // 手动签收
                    channel.basicAck(deliveryTag ,false);

                    System.out.println( "order插入成功!签收成功...." + deliveryTag );

                } catch (IOException e) {

                    // 丢弃消息
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
                }

            }

        }else {

            try {

                System.out.println("order数据已经存在!无需重新插入...直接签收消息...");

                // 手动签收
                channel.basicAck(deliveryTag ,false);

            } catch (IOException e) {

                // 丢弃消息
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }

        }

    }
}