package com.haochang.spring.cloud.alibaba.highqps.mynetty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description: 描述：netty测试
 * @author: youzhi.gao
 * @date: 2020-05-12 14:25
 */
public class MyHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("客户端发来的消息：" + s);
        channelHandlerContext.writeAndFlush("转换为大写" + s.toUpperCase());
    }
}
