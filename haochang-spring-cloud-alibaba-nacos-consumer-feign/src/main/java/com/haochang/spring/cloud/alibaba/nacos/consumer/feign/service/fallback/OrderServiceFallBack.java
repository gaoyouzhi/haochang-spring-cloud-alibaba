package com.haochang.spring.cloud.alibaba.nacos.consumer.feign.service.fallback;

import com.haochang.spring.cloud.alibaba.nacos.consumer.feign.model.Order;
import com.haochang.spring.cloud.alibaba.nacos.consumer.feign.service.OrderService;
import org.springframework.stereotype.Component;

/**
 * @description: 描述：orderService 熔断器
 * @author: youzhi.gao@ikang.com
 * @date: 2020-03-13 12:31
 */
@Component
public class OrderServiceFallBack implements OrderService {

    @Override
    public String saveOrder(Order order) {
        return "网络异常，订单处理失败。。。";
    }
}
