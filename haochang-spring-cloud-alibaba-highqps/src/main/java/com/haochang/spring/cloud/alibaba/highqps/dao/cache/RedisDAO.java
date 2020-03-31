package com.haochang.spring.cloud.alibaba.highqps.dao.cache;


import com.alibaba.fastjson.JSON;
import com.haochang.spring.cloud.alibaba.highqps.constant.RedisKey;
import com.haochang.spring.cloud.alibaba.highqps.constant.RedisKeyPrefix;
import com.haochang.spring.cloud.alibaba.highqps.model.Seckill;
import com.haochang.spring.cloud.alibaba.highqps.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class RedisDAO {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisUtil redisUtil;

//    private RuntimeSchema<Seckill> schema = MyRuntimeSchema.getInstance().getGoodsRuntimeSchema();

    public Seckill getSeckill(long seckillId) {
        //redis操作逻辑
        try {

            String key = RedisKeyPrefix.SECKILL_GOODS + seckillId;
            String value = redisUtil.get(key).toString();
            Seckill seckill = JSON.parseObject(value, Seckill.class);
            if (seckill != null) {
                //空对象
                return seckill;
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public Boolean putSeckill(Seckill seckill) {
        try {

            String key = RedisKeyPrefix.SECKILL_GOODS + seckill.getSeckillId();
            Boolean result = redisUtil.set(key, JSON.toJSONString(seckill));
            return result;

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 从缓存中获取所有的实时商品数据(包括实时库存量)
     * @return
     */
    public List<Seckill> getAllGoods() {
        List<Seckill> result = new ArrayList<>();
        Set<Object> idSet = redisUtil.sGet(RedisKey.SECKILL_ID_SET);
        if (idSet != null || idSet.size() > 0) {
            for (Object seckillId : idSet) {
                String goodsKey = RedisKeyPrefix.SECKILL_GOODS + seckillId;
                Seckill seckill = JSON.parseObject(redisUtil.get(goodsKey).toString(), Seckill.class);
                if (seckill != null) {

                    try {
                        // goodsKey获取到的库存量是初始值，并不是当前值，所有需要从RedisKeyPrefix.SECKILL_INVENTORY+seckillID
                        // 获取到的库存，再设置到结果中去
                        Object inventoryStr = redisUtil.get(RedisKeyPrefix.SECKILL_INVENTORY + seckillId);
                        if (null != inventoryStr && !StringUtils.isEmpty(inventoryStr.toString())) {
                            seckill.setInventory(Integer.valueOf(inventoryStr.toString()));
                        }
                    } catch (NumberFormatException ex) {
                        logger.error(ex.getMessage(), ex);
                    }
                    result.add(seckill);
                }
            }
        }
        return result;
    }

    public void setAllGoods(List<Seckill> list) {
        if (list == null || list.size()< 1) {
            logger.info("--FatalError!!! seckill_list_data is empty");
            return;
        }

        redisUtil.del(RedisKey.SECKILL_ID_SET);

        for (Seckill seckill : list) {
            redisUtil.sSet(RedisKey.SECKILL_ID_SET, seckill.getSeckillId() + "");

            String seckillGoodsKey = RedisKeyPrefix.SECKILL_GOODS + seckill.getSeckillId();
            System.out.println(JSON.toJSONString(seckill));
            redisUtil.set(seckillGoodsKey, JSON.toJSONString(seckill));
        }
        logger.info("数据库Goods数据同步到Redis完毕！");
    }

}
