package com.tang.mq.stock.listener;

import com.rabbitmq.client.Channel;
import com.tang.mq.stock.domain.Stock;
import com.tang.mq.stock.mapper.StockMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * @Classname StockListener
 * @Description [ 接收订单发来的订单号，开始进行减少库存 ]
 * @Author Tang
 * @Date 2019/12/28 21:49
 * @Created by ASUS
 */
@Component
public class StockListener {

    private static Integer num = 0;

    @Resource
    private StockMapper stockMapper;

    /**
     * MethodName receive
     * Description [ 接收 orderId 生成插入库存记录 ]
     * Date 2019/12/28 22:45
     * Param [orderId, hedaders, channel]
     * return
     **/
    @RabbitListener(queues = {"mq.stock.queue"})
    public void receive(Message message, @Headers Map<String, Object> headers, Channel channel) throws IOException {

        // 一对多注意幂等性问题，因为任意一个消费者失败，消费会重新投递。

        String orderId = new String(message.getBody());

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        System.out.println("stock消费者拿到orderId--->" + orderId + "进行插入数据库...");

        Stock stock = new Stock();

        stock.setStock(100);

        // 设置订单
        stock.setOrderId(orderId);

        if (StockListener.num == 0) {

            StockListener.num = (StockListener.num + 1) % 2;

            throw new RuntimeException("xx");
        }

        int value = stockMapper.insert(stock);

        // 插入成功 手动签收
        if (value > 0) {

            System.out.println("stock开始签收...." + deliveryTag);

            try {
                // 手动签收
                channel.basicAck(deliveryTag, false);

                System.out.println("stock签收成功...." + deliveryTag);

                return;

            } catch (IOException e) {
                // 丢弃消息
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            }
        }

        System.out.println("日志记录....");
    }

}