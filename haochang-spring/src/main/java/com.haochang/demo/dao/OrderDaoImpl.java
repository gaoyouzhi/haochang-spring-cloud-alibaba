package com.haochang.demo.dao;

import com.haochang.demo.model.OrderEntity;
import com.haochang.ioc.annotation.Repository;

import java.math.BigDecimal;

/**
 * @description: 描述：订单服务实现类
 * @author: youzhi.gao
 * @date: 2020-05-11 09:38
 */
@Repository
public class OrderDaoImpl implements OrderDao {

    @Override
    public OrderEntity getOrderById(Long id) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(10001L);
        orderEntity.setName("spring order");
        orderEntity.setMobile("1380000000");
        orderEntity.setPrice(new BigDecimal(10086.11));
        return orderEntity;
    }
}
