package com.haochang.spring.cloud.alibaba.nacos.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description: 描述：nacos 服务者启动类
 * @author: youzhi.gao
 * @date: 2020-03-12 15:12
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosProviderApplication.class, args);
    }
}
