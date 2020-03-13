package com.haochang.hello.spring.cloud.alibaba.nacos.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @description: 描述：nacos消费者启动类
 * @author: youzhi.gao@ikang.com
 * @date: 2020-03-12 10:20
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }


    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
