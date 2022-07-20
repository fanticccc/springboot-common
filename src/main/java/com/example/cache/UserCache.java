package com.example.cache;

import com.example.config.cache.CacheProperties;
import com.example.pojo.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author song
 * @Description: 用户缓存
 * @Date 2022/5/23 10:34
 * @Version 1.0
 * com.example.cache
 */
@Service
public class UserCache implements InitializingBean {

    private static final int DEFAULT_MAX_SIZE =5000 ;

    private static final int DEFAULT_CACHE_TIME =100 ;

    private Cache<String,User> cache;

   @Autowired
   private CacheProperties cacheProperties ;
    /**
     * 缓存配置
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        cache = CacheBuilder.newBuilder()
                .maximumSize(cacheProperties.getCACHE_SIZE() == null ? DEFAULT_MAX_SIZE : cacheProperties.getCACHE_SIZE())
                .expireAfterAccess(cacheProperties.getCACHE_TIME() == null ? DEFAULT_CACHE_TIME : cacheProperties.getCACHE_TIME(),TimeUnit.SECONDS)
                .build();
    }
    /**
     * 存储
     * @param name key
     * @param user value
     */
    public void add(String name,User user){
        cache.put(name,user);
    }

    /**
     * 获取
     * @param name key
     * @return 获取缓存
     */
    public User get(String name){
        return cache.getIfPresent(name);
    }
}
