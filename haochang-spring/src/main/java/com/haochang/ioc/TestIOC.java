package com.haochang.ioc;

import com.haochang.demo.model.OrderEntity;
import com.haochang.demo.service.OrderService;
import com.haochang.ioc.core.AnnotationApplicationContext;

/**
 * @description: 描述：测试ioc
 * @author: youzhi.gao
 * @date: 2020-05-11 11:06
 */
public class TestIOC {
    public static void main(String[] args) {
        AnnotationApplicationContext annotationApplicationContext = new AnnotationApplicationContext();
        OrderService orderService = (OrderService) annotationApplicationContext.getBeanByName("orderServiceImpl");
        System.out.println(orderService);
        OrderEntity order = orderService.getOrderById(11111L);
        System.out.println(order.toString());
    }
}
