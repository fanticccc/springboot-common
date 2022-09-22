package com.example.config.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Slf4j
@Service
public class RocketMQPublisher {
    @Autowired

    private RocketMqProperties properties;
    @Autowired
    private RocketMqConfig rocketMqConfig;

    public void send(String message) {
        Message msg = new Message(properties.getTopic(), message.getBytes(StandardCharsets.UTF_8));
        try {
            SendResult send = rocketMqConfig.getDefaultmqproducer().send(msg);
            log.info("send message to MQBrocker success:{}", send);
        } catch (MQClientException e) {
            e.printStackTrace();
            log.error("MQ 客户端异常：{}", e.getErrorMessage());
        } catch (RemotingException e) {
            e.printStackTrace();
            log.error("MQ 路由异常：{}", e.getMessage());
        } catch (MQBrokerException e) {
            e.printStackTrace();
            log.error("MQ Brocker异常：{}", e.getErrorMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            log.error("MQ 异常：{}", e.getMessage());
        }
    }
}
