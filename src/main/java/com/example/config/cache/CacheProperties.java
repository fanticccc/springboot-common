package com.example.config.cache;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author YSTen_SongJunBao
 * @Description:
 * @Date 2022/6/14 11:18
 * @Version 1.0
 * com.example.config.cache
 */
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
