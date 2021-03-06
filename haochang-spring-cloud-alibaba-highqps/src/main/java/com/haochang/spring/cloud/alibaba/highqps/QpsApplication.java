package com.haochang.spring.cloud.alibaba.highqps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 描述：qps应用启动类
 * @author: youzhi.gao
 * @date: 2020-03-25 17:35
 */
@SpringBootApplication
//@EnableFeignClients
@EnableDiscoveryClient
public class QpsApplication {

    public static void main(String[] args) {
        SpringApplication.run(QpsApplication.class, args);
    }
}
