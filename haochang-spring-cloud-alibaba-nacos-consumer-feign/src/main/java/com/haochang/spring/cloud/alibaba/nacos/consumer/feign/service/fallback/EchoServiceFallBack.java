package com.haochang.spring.cloud.alibaba.nacos.consumer.feign.service.fallback;

import com.haochang.spring.cloud.alibaba.nacos.consumer.feign.service.EchoService;
import org.springframework.stereotype.Component;

/**
 * @description: 描述：熔断service
 * @author: youzhi.gao@ikang.com
 * @date: 2020-03-13 09:31
 */
@Component
public class EchoServiceFallBack implements EchoService {

    @Override
    public String echo(String message) {

        return "网络出现错误，请稍后再试。。。";
    }
}
