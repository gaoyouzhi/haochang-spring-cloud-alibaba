package com.haochang.spring.cloud.alibaba.highqps.service;

import com.haochang.spring.cloud.alibaba.highqps.model.Goods;

import java.util.List;

/**
 * @ClassName: QpsService
 * @Description TODO
 * @Author: youzhi.gao
 * @Date: 2020-03-30 13:59
 * @Version 1.0.0
 */
public interface QpsService {
    List<Goods> queryGoods();

    Goods queryGoodsById(Long id);
}
