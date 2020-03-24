package com.haochang.spring.cloud.alibaba.nacos.consumer.feign.test;

import org.apache.commons.lang.StringUtils;

/**
 * @description: 描述：string test
 * @author: youzhi.gao
 * @date: 2020-03-13 17:58
 */
public class StringTest {
    public static void main(String[] args) {
        String a = "";
        System.out.println(testStr(a));

    }

    private static boolean testStr(String a) {
        if (StringUtils.isEmpty(a) || !a.matches("[0-9]{11}")) {
            return false;
        }
        return true;
    }
}
