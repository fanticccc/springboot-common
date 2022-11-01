package com.example.config.rocketmq;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author SongJunBao
 * @Description:
 * @Date 2022/3/25 18:14
 * @Version 1.0
 * com.exc.song.config.rocketmq
 */
@Configuration
public class RocketMqFactory {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public RocketMqConfig createRocketMqProducer() {
        return new RocketMqConfig();
    }
}
