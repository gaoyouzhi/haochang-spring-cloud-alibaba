package com.haochang.spring.cloud.alibaba.nacos.provider.controller;

import com.haochang.spring.cloud.alibaba.nacos.provider.model.Order;
import com.haochang.spring.cloud.alibaba.nacos.provider.service.NacosProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


/**
 * @description: 描述：nacos服务控制器
 * @author: youzhi.gao@ikang.com
 * @date: 2020-03-12 17:05
 */
@RestController
public class NacosProviderController {

    @Autowired
    private NacosProviderService nacosProviderService;

    @GetMapping("/hi")
    public String getNacosProviderName(){
        return nacosProviderService.getProviderName();
    }

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/echo/{message}")
    public String echo(@PathVariable String message) {
        return "Hello Nacos Discovery " + message + "I am from " + port;
    }

    @PostMapping(value = "/provider/saveOrder")
    public String saveOrder(@RequestBody Order order) {
        return "This is the order infomation : " + order.toString() + ". I am from " + port;
    }
}
