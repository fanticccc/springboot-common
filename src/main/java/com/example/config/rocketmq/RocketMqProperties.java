package com.example.config.rocketmq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author YSTen_SongJunBao
 * @Description:
 * @Date 2022/3/25 18:14
 * @Version 1.0
 * com.exc.song.config.rocketmq
 */
@Data
@Component
//@PropertySource("application.yml")
@ConfigurationProperties(prefix = "system-parameters.rocketmq")

public class RocketMqProperties {
    private String ipPort = "127.0.0.1:9876";
    private String topic = "";
    private String gdProduceGroup = "";
}
