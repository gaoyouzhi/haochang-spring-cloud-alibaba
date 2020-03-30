package com.haochang.spring.cloud.alibaba.highqps.mq;import com.alibaba.fastjson.JSON;import com.haochang.spring.cloud.alibaba.highqps.bean.MQConfigBean;import com.haochang.spring.cloud.alibaba.highqps.model.SeckillMsgBody;import com.rabbitmq.client.Channel;import com.rabbitmq.client.MessageProperties;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Component;import java.io.IOException;import java.util.concurrent.TimeoutException;@Componentpublic class MQProducer {    private final Logger logger = LoggerFactory.getLogger(this.getClass());    @Autowired    private MQChannelManager mqChannelManager;//    @Resource(name = "initJedisPool")//    private JedisPool jedisPool;    @Autowired    private MQConfigBean mqConfigBean;    public void send(SeckillMsgBody body) {        String msg = JSON.toJSONString(body);        //获取当前线程使用的Rabbitmq通道        Channel channel = mqChannelManager.getSendChannel();        try {            logger.info(" [mqSend] '" + msg + "'");            channel.confirmSelect();            channel.basicPublish("",                    mqConfigBean.getQueue(),                    MessageProperties.PERSISTENT_TEXT_PLAIN,                    msg.getBytes());        } catch (IOException e) {            e.printStackTrace();        }        boolean sendAcked = false;        try {            sendAcked = channel.waitForConfirms(100);        } catch (InterruptedException e) {            e.printStackTrace();        } catch (TimeoutException e) {            e.printStackTrace();        }        logger.info("sendAcked={}", sendAcked);        if (sendAcked) {//            Jedis jedis = jedisPool.getResource();//            jedis.sadd(RedisKey.QUEUE_PRE_SECKILL, body.getSeckillId() + "@" + body.getUserPhone());//            jedis.close();        } else {            logger.info("!!!mqSend_NACKED,NOW_RETRY>>>");            try {                channel.basicPublish("",                        mqConfigBean.getQueue(),                        MessageProperties.PERSISTENT_TEXT_PLAIN,                        msg.getBytes());            } catch (IOException e) {                e.printStackTrace();            }        }    }}