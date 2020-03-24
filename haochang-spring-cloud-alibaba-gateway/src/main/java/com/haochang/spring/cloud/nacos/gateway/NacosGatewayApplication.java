package com.haochang.spring.cloud.nacos.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 描述：网关启动类
 * @author: youzhi.gao
 * @date: 2020-03-20 15:29
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class NacosGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosGatewayApplication.class, args);
    }
}
