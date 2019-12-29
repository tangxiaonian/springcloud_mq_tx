package com.tang.mq.stock.service;

import com.tang.mq.stock.domain.Stock;
import com.tang.mq.stock.mapper.StockMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Classname StockService
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/24 18:24
 * @Created by ASUS
 */
@Service
public class StockService {

    @Resource
    private StockMapper stockMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    public void update() {

        Stock stock = stockMapper.selectByPrimaryKey(1);

        stock.setStock( stock.getStock() - 1 );

        stockMapper.updateByPrimaryKey(stock);

    }

}