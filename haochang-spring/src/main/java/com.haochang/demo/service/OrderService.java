package com.haochang.demo.service;

import com.haochang.demo.model.OrderEntity;

/**
 * @description: 描述：订单服务service
 * @author: youzhi.gao
 * @date: 2020-05-11 09:40
 */
public interface OrderService {
    OrderEntity getOrderById(Long id);
}
