//package com.haochang.spring.cloud.alibaba.highqps.singleton;
//
//import com.haochang.spring.cloud.alibaba.highqps.model.Seckill;
//import io.protostuff.runtime.RuntimeSchema;
//
//public class MyRuntimeSchema {
//    private static MyRuntimeSchema ourInstance = new MyRuntimeSchema();
//
//    private RuntimeSchema<Seckill> goodsRuntimeSchema;
//
//
//    public static MyRuntimeSchema getInstance() {
//        return ourInstance;
//    }
//
//    private MyRuntimeSchema() {
//        RuntimeSchema<Seckill> seckillSchemaVar = RuntimeSchema.createFrom(Seckill.class);
//        setGoodsRuntimeSchema(seckillSchemaVar);
//    }
//
//    public RuntimeSchema<Seckill>
//    getGoodsRuntimeSchema() {
//        return goodsRuntimeSchema;
//    }
//
//    private void setGoodsRuntimeSchema(RuntimeSchema<Seckill> goodsRuntimeSchema) {
//        this.goodsRuntimeSchema = goodsRuntimeSchema;
//    }
//}
