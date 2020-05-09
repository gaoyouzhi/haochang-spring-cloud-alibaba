package com.haochang.spring.cloud.alibaba.highqps.extension.utils;

/**
 * @description: 描述：holder
 * @author: youzhi.gao
 * @date: 2020-05-09 14:50
 */
public class Holder<T> {
    private volatile T value;

    public Holder() {
    }

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
