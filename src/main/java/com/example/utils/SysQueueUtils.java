package com.example.utils;

import com.example.pojo.Grade;
import com.example.pojo.User;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author song
 * @Description: 延时队列 存放异常用户
 * @Date 2022/2/24 14:53
 * @Version 1.0
 * com.example.utils
 */
@Slf4j
public class SysQueueUtils {

    private static final int QUEUE_SIZE = 100;

    private final static ArrayBlockingQueue<Grade> queue = new ArrayBlockingQueue<>(QUEUE_SIZE);

    /**
     * 保存信息至队列
     *
     * @param user
     * @return
     */
    public static Boolean setObj(Grade user) {
        return queue.offer(user);
    }

    /**
     * 获取队列信息
     *
     * @return
     * @throws InterruptedException
     */
    public static Grade getObj() throws InterruptedException {
        return queue.take();
    }
}
