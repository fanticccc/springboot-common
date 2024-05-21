package com.example.test;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Author: SongJunBao@pukkasoft.cn
 * @Date: 2024/4/18 0:11
 * @Package: com.example.test
 * @Description: -TODO
 */
@Component
public class RunnerTest implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //可以拿到ApplicationContext
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        System.out.println("执行Runner ------------");
    }
}