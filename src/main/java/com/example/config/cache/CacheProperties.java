package com.example.config.cache;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "system-parameters.cache")
@Data
public class CacheProperties {
    /**
     *缓存条数
     */
    private Integer CACHE_SIZE ;
    /**
     * 缓存时间
     */
    private Integer CACHE_TIME ;
}
