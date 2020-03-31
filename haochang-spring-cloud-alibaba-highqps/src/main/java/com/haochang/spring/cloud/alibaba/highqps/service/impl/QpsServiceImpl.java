package com.haochang.spring.cloud.alibaba.highqps.service.impl;

import com.haochang.spring.cloud.alibaba.highqps.fallback.GoodsFallBackServiceImpl;
import com.haochang.spring.cloud.alibaba.highqps.mapper.GoodsMapper;
import com.haochang.spring.cloud.alibaba.highqps.model.Goods;
import com.haochang.spring.cloud.alibaba.highqps.service.QpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 描述：qpsService实现类
 * @author: youzhi.gao
 * @date: 2020-03-30 13:59
 */
//@FeignClient(fallback = GoodsFallBackServiceImpl.class)
@Service
public class QpsServiceImpl implements QpsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Goods> queryGoods() {
        return goodsMapper.queryGoods();
    }

    @Override
    public Goods queryGoodsById(Long id) {
        return goodsMapper.queryGoodsById(id);
    }
}
