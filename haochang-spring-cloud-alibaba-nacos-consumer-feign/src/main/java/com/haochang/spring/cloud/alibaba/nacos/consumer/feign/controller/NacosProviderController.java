package com.haochang.spring.cloud.alibaba.nacos.consumer.feign.controller;

import com.haochang.spring.cloud.alibaba.nacos.consumer.feign.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 描述：nacos Provider 测试熔断
 * @author: youzhi.gao
 * @date: 2020-03-13 09:29
 */
@RestController
public class NacosProviderController {

    @Autowired
    private EchoService echoService;

    @GetMapping("/echo/{message}")
    public String echo(@PathVariable String message){
        return echoService.echo(message);
    }


}
