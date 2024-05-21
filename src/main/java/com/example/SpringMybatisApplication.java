package com.example;

import com.example.consumer.starter.ExplainService;
import com.example.pojo.User;
import com.example.service.GradeService;
import com.example.thread.SyncTaskThread;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
//@SpringBootApplication(exclude = RocketMqConfig.class)
@SpringBootApplication()
@MapperScan("com.example.mapper")
@EnableCaching // 开启基于注解的缓存
@EnableScheduling // 开启定时任务
@EnableAsync

public class SpringMybatisApplication extends SpringBootServletInitializer implements ApplicationRunner {
    @Autowired
    private GradeService gradeService;
    @Getter
    private static ConfigurableApplicationContext ctx;
    @Autowired
    private ExplainService explainService;

    public static void main(String[] args) throws UnknownHostException {
//        SpringApplication.run(SpringMybatisApplication.class, args);
        ctx = SpringApplication.run(SpringMybatisApplication.class, args);
        Environment environment = ctx.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "应用访问连接:\t\thttp://{}:{}{}/\n\t" +
                        "swagger地址: \t\thttp://{}:{}{}/swagger-ui.html\n\t" +
                        "--------------------------------------------------------------",
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"), "/song",
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"), "/song"
        );
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info(" springboot project start  。。。 ");
        //启动服务的时候 将线程开启 处理异常用户信息
        SyncTaskThread syncTaskThread = new SyncTaskThread(gradeService);
        syncTaskThread.start();
    }

    /**
     * 测试自定义stater
     * @param args
     * @throws Exception
     */
    /*@Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner start . . .");
        explainService.explain("李四","springboot starter。。。");
    }*/
}
