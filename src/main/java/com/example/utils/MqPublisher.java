/*
package com.example.utils;

import com.example.config.rocketmq.RocketMqConfig;
import com.example.config.rocketmq.RocketMqProperties;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

*/
/**
 * @Description:
 * @Date 2022/4/6 10:45
 * @Version 1.0
 * com.exc.song.utils
 * <p>
 * 发送消息
 * @param message
 *//*

@Slf4j
@Service
public class MqPublisher {
    private static ThreadLocal threadLocal = new ThreadLocal();
    private RocketMqConfig rocketMqConfig;
    private RocketMqProperties rocketMqProperties;

    @Autowired
    public void setRocketMqProperties(RocketMqProperties rocketMqProperties){
        this.rocketMqProperties = rocketMqProperties ;
    }
    @Autowired
    public void setRocketMqConfig(RocketMqConfig rocketMqConfig) {
        this.rocketMqConfig = rocketMqConfig;
    }

    */
/**
 * 发送消息
 * @param message
 *//*

    public void send(String message) {
        try {
            Message msg = new Message(rocketMqProperties.getTopic(), message.getBytes(StandardCharsets.UTF_8));
            SendResult sendResult = rocketMqConfig.getDefaultmqproducer().send(msg);
            threadLocal.set(message);
            log.info("消息发送结果：{}", sendResult);
        } catch (Exception e) {
            log.error("RocketMq 发送信息报错，错误信息为:" + e);
        }
    }

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }
}
*/
