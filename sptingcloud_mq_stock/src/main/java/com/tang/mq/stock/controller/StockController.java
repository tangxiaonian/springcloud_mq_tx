package com.tang.mq.stock.controller;

import com.tang.mq.stock.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Classname StokController
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/23 19:26
 * @Created by ASUS
 */
@RequestMapping("/stock")
@RestController
public class StockController {

    @Resource
    private StockService stockService;

    @GetMapping("/reduce")
    public String reduceNumber() {

        stockService.update();

        return "success";
    }

}