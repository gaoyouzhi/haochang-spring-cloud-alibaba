package com.haochang.spring.cloud.alibaba.highqps.extension.annotation;

import java.lang.annotation.*;

/**
 * @ClassName: SPI
 * @Description TODO
 * @Author: youzhi.gao
 * @Date: 2020-05-09 14:20
 * @Version 1.0.0
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SPI {
    String value() default "";
}
