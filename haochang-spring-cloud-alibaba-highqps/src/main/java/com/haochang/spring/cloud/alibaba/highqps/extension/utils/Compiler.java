package com.haochang.spring.cloud.alibaba.highqps.extension.utils;

import com.haochang.spring.cloud.alibaba.highqps.extension.annotation.SPI;

/**
 * @description: 描述：编译工具类
 * @author: youzhi.gao
 * @date: 2020-05-09 15:59
 */
@SPI("javassist")
public interface Compiler {
    Class<?> compiler(String var1, ClassLoader var2);
}
