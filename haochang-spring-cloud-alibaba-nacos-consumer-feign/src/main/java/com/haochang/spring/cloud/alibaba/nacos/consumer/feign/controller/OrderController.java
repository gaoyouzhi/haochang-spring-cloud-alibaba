package com.haochang.spring.cloud.alibaba.nacos.consumer.feign.controller;

import com.haochang.spring.cloud.alibaba.nacos.consumer.feign.model.Order;
import com.haochang.spring.cloud.alibaba.nacos.consumer.feign.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 描述：处理订单controller
 * @author: youzhi.gao@ikang.com
 * @date: 2020-03-13 12:32
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/saveOrder")
    public String saveOrder(@RequestBody Order order){
        return orderService.saveOrder(order);
    }


}
