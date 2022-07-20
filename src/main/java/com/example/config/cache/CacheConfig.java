package com.example.config.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 缓存配置信息
 * @Date 2022/1/26 11:02
 * @Version 1.0
 * com.example.config
 */
@EnableCaching
@Configuration
public class CacheConfig {
    @Bean
    public ConcurrentMapCacheManager cacheManager(){
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setStoreByValue(true); //true表示缓存一份副本，否则缓存引用
        return cacheManager;
    }
}
