package com.example.config.threadPool;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Hashtable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author song
 * @Description: 线程池使用
 * @Date 2022/6/14 10:27
 * @Version 1.0
 * com.example.config.thread
 */
@Slf4j
@EnableAsync
@EnableConfigurationProperties(ThreadPoolProperties.class)
@Configuration
public class ThreadPoolConfig {

    private ThreadPoolProperties threadPoolProperties;

    @Autowired
    public void setThreadPoolProperties(ThreadPoolProperties threadPoolProperties) {
        this.threadPoolProperties = threadPoolProperties;
    }

    @Bean(name = "adminThreadPool")
    public ThreadPoolExecutor getThreadPoolExecutor() {
        return initThreadPool2("emailThreadPool--");
    }

    private ThreadPoolTaskExecutor initThreadPool(String poolName) {
        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
        );*/
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        executor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        executor.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
        executor.setQueueCapacity(threadPoolProperties.getQueueCapactiy());
        // 拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    private ThreadPoolExecutor initThreadPool2(String poolName) {
        CorsFilter corsFilter = new CorsFilter();
        ThreadFactory threadFactory = new CustomizableThreadFactory(poolName);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                threadPoolProperties.getCorePoolSize(),
                threadPoolProperties.getMaxPoolSize(),
                threadPoolProperties.getKeepAliveSeconds(),
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(threadPoolProperties.getQueueCapactiy()),
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return executor;
    }
}
