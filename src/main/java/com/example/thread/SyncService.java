package com.example.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author SongJunBao
 * @Description:
 * @Date 2022/8/5 14:46
 * @Version 1.0
 * com.example.thread
 */
@Service
@Slf4j
public class SyncService {

    @Async("adminThreadPool")
    public void dealMsgBySync() throws InterruptedException {
        Thread.sleep(1000);
        log.info("sync do ....." +Thread.currentThread().getName());
    }
}
