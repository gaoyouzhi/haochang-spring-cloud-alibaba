package com.haochang.spring.cloud.alibaba.nacos.consumer.feign.service;

import com.haochang.spring.cloud.alibaba.nacos.consumer.feign.model.Order;
import com.haochang.spring.cloud.alibaba.nacos.consumer.feign.service.fallback.OrderServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName: OrderService
 * @Description TODO
 * @Author: youzhi.gao
 * @Date: 2020-03-13 12:29
 * @Version 1.0.0
 */
@FeignClient(value = "haochang-nacos-provider", fallback = OrderServiceFallBack.class)
public interface OrderService {

    @PostMapping(value = "/provider/saveOrder")
    public String saveOrder(@RequestBody Order order);
}
