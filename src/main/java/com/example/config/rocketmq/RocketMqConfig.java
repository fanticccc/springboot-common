package com.example.config.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author YSTen_SongJunBao
 * @Description:
 * @Date 2022/3/25 18:13
 * @Version 1.0
 * com.exc.song.config.rocketmq
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(RocketMqProperties.class)
public class RocketMqConfig {
    private RocketMqProperties rocketMqProperties;

    private DefaultMQProducer defaultmqproducer;

    private RocketMqFactory rocketMqFactory;

    public void init() throws MQClientException {
        log.info("RocketMqProducer initialize!");
        // 初始化
        defaultmqproducer = new DefaultMQProducer(rocketMqProperties.getGdProduceGroup());
        defaultmqproducer.setNamesrvAddr(rocketMqProperties.getIpPort());
        defaultmqproducer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        defaultmqproducer.start();
        log.info("RocketMqProducer start success!");

    }

    public void destroy() {
        defaultmqproducer.shutdown();
    }

    public DefaultMQProducer getDefaultmqproducer() {
        return rocketMqFactory.createRocketMqProducer().defaultmqproducer;
    }

    @Autowired
    public void setRocketMqFactory(RocketMqFactory rocketMqFactory) {
        this.rocketMqFactory = rocketMqFactory;
    }

    @Autowired
    public void setRocketMqProperties(RocketMqProperties rocketMqProperties) {
        this.rocketMqProperties = rocketMqProperties;
    }
}
