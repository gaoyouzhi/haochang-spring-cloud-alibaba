package com.haochang.hello.spring.cloud.alibaba.nacos.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 描述：nacos 消费者控制器
 * @author: youzhi.gao
 * @date: 2020-03-12 15:02
 */
@RestController
public class NacosConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    private String appName;

    @GetMapping("/echo/app/name")
    public String echo(){
        ServiceInstance serviceId = loadBalancerClient.choose("nacos-provider");
        String url = String.format("http://%s:%s/echo" , serviceId.getHost(), serviceId.getPort());
        return restTemplate.getForObject(url, String.class);
    }


}
