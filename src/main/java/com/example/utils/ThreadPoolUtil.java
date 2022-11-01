package com.example.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author SongJunBao
 * @Description:
 * @Date 2022/6/17 17:27
 * @Version 1.0
 * com.example.utils
 */

public class ThreadPoolUtil {
    /**
     * 核心线程数
     */
    private static final int SIZE_CORE_POOL = 5;
    /**
     * 最大线程数
     */
    private static final int SIZE_MAX_POOL = 10;
    /**
     * 辅助线程空闲时的存活时间
     */
    private static final long ALIVE_TIME = 2000;
    /**
     * 时间单位
     */
    private static TimeUnit UNIT = TimeUnit.MILLISECONDS;
    /**
     * 阻塞队列
     */
    private static BlockingQueue<Runnable> QUEUE = new ArrayBlockingQueue<Runnable>(100);
    /**
     * 进入线程池由main线程执行的任务个数
     */
    public static AtomicInteger mainTaskNum = new AtomicInteger(0);
    /**
     * 创建线程池，初始化参数
     */
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            SIZE_CORE_POOL,
            SIZE_MAX_POOL,
            ALIVE_TIME,
            UNIT,
            QUEUE,
            new BackendCallerRunsPolicy()
    );

    static {
        pool.prestartAllCoreThreads();
    }

    /**
     * 获取线程池方法
     *
     * @return ThreadPoolExecutor
     */
    public static ThreadPoolExecutor getPool() {
        return pool;
    }

    public static class BackendCallerRunsPolicy extends ThreadPoolExecutor.CallerRunsPolicy {
        public BackendCallerRunsPolicy() {
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            super.rejectedExecution(r, e);
            mainTaskNum.getAndIncrement();
        }
    }
}
