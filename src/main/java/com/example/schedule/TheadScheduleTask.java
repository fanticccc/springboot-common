package com.example.schedule;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author YSTen_SongJunBao
 * @Description:
 * @Date 2022/1/19 16:52
 * @Version 1.0
 * com.example.schedule
 */
@Component
@EnableScheduling //开启定时任务
@EnableAsync //开启多线程
public class TheadScheduleTask {
    @Async("UserThreadPoolExecutor")
    @Scheduled(fixedDelay = 5000)
    public void first(){
        System.out.println(" Scheduled start:"+ LocalDateTime.now().toLocalTime()+"\r\n线程"+Thread.currentThread().getName());

    }
    /* @Async
    @Scheduled(fixedDelay = 2000)
    public void second() {
        System.out.println("second Scheduled start : " + LocalDateTime.now().toLocalTime() + "\r\n线程 : " + Thread.currentThread().getName());

    }*/
}
