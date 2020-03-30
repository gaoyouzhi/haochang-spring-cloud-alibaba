package com.haochang.spring.cloud.alibaba.highqps.fallback;

import com.haochang.spring.cloud.alibaba.highqps.model.Goods;
import com.haochang.spring.cloud.alibaba.highqps.service.QpsService;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

/**
 * @description: 描述：服务调用失败提示
 * @author: youzhi.gao
 * @date: 2020-03-30 14:10
 */
@FeignClient
public class GoodsFallBackServiceImpl implements QpsService {
    @Override
    public List<Goods> queryGoods() {
        return null;
    }

    @Override
    public Goods queryGoodsById(Long id) {
        return null;
    }
}
