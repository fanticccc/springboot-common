package com.example.schedule;

import com.example.thread.SyncTaskThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

/**
 * @Author YSTen_SongJunBao
 * @Description: 启动类
 * @Date 2022/2/24 17:58
 * @Version 1.0
 * com.example.schedule
 */
@Slf4j
@Configuration
public class SaveGradeScheduleTask implements ApplicationContextAware {

   /* @Autowired
    private SyncTaskThread syncTaskThread;*/
    private static ApplicationContext context ;

    @Scheduled(cron = "${system-parameters.scheduling.grade}")
    public void task(){
        //SyncTaskThread syncTaskThread = (SyncTaskThread) getBean("syncTaskThread");
        // SyncTaskThread syncTaskThread1 = new SyncTaskThread();
        log.info("Spring Scheduling SaveGradeScheduleTask start--------:{}", LocalDateTime.now().toLocalTime() );
        //syncTaskThread.start();
        //syncTaskThread1.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext ;
    }

    public static ApplicationContext getApplicationContent(){
        return context ;
    }

    public static Object getBean(String name){
        return getApplicationContent().getBean(name);
    }
}
