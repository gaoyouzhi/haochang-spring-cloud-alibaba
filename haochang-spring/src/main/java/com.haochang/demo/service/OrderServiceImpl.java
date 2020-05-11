package com.haochang.demo.service;

import com.haochang.demo.dao.OrderDao;
import com.haochang.demo.model.OrderEntity;
import com.haochang.ioc.annotation.Autowired;
import com.haochang.ioc.annotation.Service;

/**
 * @description: 描述：订单服务service实现类
 * @author: youzhi.gao
 * @date: 2020-05-11 09:41
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public OrderEntity getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }
}
