package com.haochang.spring.cloud.alibaba.highqps.mynetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @description: 描述：测试netty
 * @author: youzhi.gao
 * @date: 2020-05-12 14:32
 */
public class HelloNetty {
    private static int port = 8888;

    public static void main(String[] args) {
        EventExecutorGroup bossGroup = new NioEventLoopGroup();
        EventExecutorGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
//        bootstrap.group(bossGroup, workerGroup);
        bootstrap.childHandler(new MyServerInitializer());
//        ChannelFuture future = bootstrap.bind(port).sync().addListener();
    }
}
