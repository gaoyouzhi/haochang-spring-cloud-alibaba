package com.haochang.spring.cloud.alibaba.nacos.consumer.feign.service;

import com.haochang.spring.cloud.alibaba.nacos.consumer.feign.service.fallback.EchoServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: EchoService
 * @Description TODO
 * @Author: youzhi.gao
 * @Date: 2020-03-12 17:38
 * @Version 1.0.0
 */
@FeignClient(value = "haochang-nacos-provider", fallback = EchoServiceFallBack.class)
public interface EchoService {

    @GetMapping("/echo/{message}")
    public String echo(@PathVariable("message") String message);
}
