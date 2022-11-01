package com.example.config;

import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author SongJunBao
 * @Description:
 * @Date 2022/1/27 15:33
 * @Version 1.0
 * com.example.config
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Cacheable(value = "users",key = "#id",condition = "#id<5")
public @interface MyCache {
    // String[] value();
}
