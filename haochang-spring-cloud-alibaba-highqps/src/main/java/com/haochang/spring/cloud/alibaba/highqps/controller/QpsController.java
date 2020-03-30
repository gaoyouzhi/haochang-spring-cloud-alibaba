package com.haochang.spring.cloud.alibaba.highqps.controller;

import com.haochang.spring.cloud.alibaba.highqps.model.Goods;
import com.haochang.spring.cloud.alibaba.highqps.service.QpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 描述：测试qps和tps
 * @author: youzhi.gao
 * @date: 2020-03-30 13:57
 */
@RestController("/qps")
public class QpsController {

    @Autowired
    private QpsService qpsService;

    @RequestMapping("/queryGoodsById/{id}")
    public Goods queryGoodsById(@PathVariable(value = "id") Long id){
        return qpsService.queryGoodsById(id);
    }
}
