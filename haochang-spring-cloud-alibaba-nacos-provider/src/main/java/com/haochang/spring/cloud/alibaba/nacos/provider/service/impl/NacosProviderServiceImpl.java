package com.haochang.spring.cloud.alibaba.nacos.provider.service.impl;

import com.haochang.spring.cloud.alibaba.nacos.provider.service.NacosProviderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @description: 描述：服务提供者实现类
 * @author: youzhi.gao@ikang.com
 * @date: 2020-03-12 17:03
 */
@Service
public class NacosProviderServiceImpl implements NacosProviderService {

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public String getProviderName() {
        return appName;
    }
}
