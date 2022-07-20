package com.example.config.thread;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author song
 * @Description:
 * @Date 2022/6/14 10:27
 * @Version 1.0
 * com.example.config.thread
 */
@Data
@Component
@ConfigurationProperties(prefix = "system-parameters.thread-pool")
public class ThreadPoolProperties {
    /**
     * 核心线程数
     */
    private int corePoolSize;
    /**
     * 最大线程数
     */
    private int maxPoolSize;
    /**
     * 存活时间
     */
    private int keepAliveSeconds;
    /**
     * queue
     */
    private int queueCapactiy;
}
