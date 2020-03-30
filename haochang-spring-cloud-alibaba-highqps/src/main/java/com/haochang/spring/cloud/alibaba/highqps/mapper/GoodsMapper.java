package com.haochang.spring.cloud.alibaba.highqps.mapper;

import com.haochang.spring.cloud.alibaba.highqps.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ClassName: GoodsMapper
 * @Description TODO
 * @Author: youzhi.gao
 * @Date: 2020-03-30 14:02
 * @Version 1.0.0
 */
@Mapper
@Repository
public interface GoodsMapper {

    @Select("select * from goods")
    List<Goods> queryGoods();

    @Select("select * from goods where id = #{id}")
    Goods queryGoodsById(Long id);
}
