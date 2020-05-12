package com.haochang.spring.cloud.alibaba.highqps.mynetty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @description: 描述：初始化channel
 * @author: youzhi.gao
 * @date: 2020-05-12 14:27
 */
public class MyServerInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        //获取管道流
        ChannelPipeline channelPipeline = channel.pipeline();
        //以"/n /r" 结尾进行一个分割，主要为了解决粘包问题
        channelPipeline.addLast("framer", new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
        channelPipeline.addLast("decoder", new StringDecoder());
        channelPipeline.addLast("encoder", new StringEncoder());
        channelPipeline.addLast("handler", new MyHandler());
    }
}
