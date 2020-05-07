package com.haochang.spring.cloud.alibaba.rocketmq.provider.test;

/**
 * @description: 描述：threadLocal实战
 * @author: youzhi.gao
 * @date: 2020-05-06 10:29
 */
public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("name" + ThreadLocalTest.class.getName());

    }
}
