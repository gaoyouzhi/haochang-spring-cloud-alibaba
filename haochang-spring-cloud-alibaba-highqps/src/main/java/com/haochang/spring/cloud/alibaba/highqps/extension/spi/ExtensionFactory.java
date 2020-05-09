package com.haochang.spring.cloud.alibaba.highqps.extension.spi;

import com.haochang.spring.cloud.alibaba.highqps.extension.annotation.SPI;

/**
 * @description: 描述：扩展工厂类
 * @author: youzhi.gao
 * @date: 2020-05-09 14:29
 */
@SPI
public interface ExtensionFactory {
    <T> T getExtension(Class<T> var1, String var2);
}
