package com.example.config.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author YSTen_SongJunBao
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
    public void setThreadPoolProperties(ThreadPoolProperties threadPoolProperties){
        this.threadPoolProperties = threadPoolProperties ;
    }

    @Bean(name = "UserThreadPoolExecutor")
    public AsyncTaskExecutor getThreadPoolAsync(){
        return initThreadPool("UserThreadPoolExecutor");
    }

    private ThreadPoolTaskExecutor initThreadPool(String poolName){
        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
        );*/
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        executor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        executor.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
        executor.setQueueCapacity(threadPoolProperties.getQueueCapactiy());
        // 拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor ;
    }
    private ThreadPoolExecutor initThreadPool2(String poolName){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                threadPoolProperties.getCorePoolSize(),
                threadPoolProperties.getMaxPoolSize(),
                threadPoolProperties.getKeepAliveSeconds(),
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(threadPoolProperties.getQueueCapactiy()),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return executor;
    }
}
