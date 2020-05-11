package com.haochang.ioc.core;

/**
 * @description: 描述：ioc上下文
 * @author: youzhi.gao
 * @date: 2020-05-11 09:48
 */
public abstract class ApplicationContext {

    /**
     * 通过容器中的别名称
     * @param name
     * @return
     */
    public Object getBeanByName(String name){
        return doGetBean(name);
    }

    protected abstract Object doGetBean(String name);
}
