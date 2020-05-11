package com.haochang.demo.dao;

import com.haochang.demo.model.OrderEntity;

/**
 * @ClassName: OrderDao
 * @Description TODO
 * @Author: youzhi.gao
 * @Date: 2020-05-11 09:35
 * @Version 1.0.0
 */
public interface OrderDao {
    OrderEntity getOrderById(Long id);
}
