package com.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.ResourceBundle;

/**
 * @Author song
 * @Description: 手动挂起服务
 * @Date 2022/2/23 17:52
 * @Version 1.0
 * com.example.controller
 */
@Slf4j
@RestController
public class ShutDownController {

    @Autowired
    private ApplicationContext context;

    @GetMapping("/stop")
    public void stop() {
        log.info(" ============== enter  SpringBoot ================ ");
        ConfigurableApplicationContext ctx = (ConfigurableApplicationContext) context;
        ctx.close();
        log.info(" ============== exit   SpringBoot ================ ");
    }
}
