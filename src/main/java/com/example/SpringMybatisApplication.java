package com.example;

import com.example.config.rocketmq.RocketMqConfig;
import com.example.config.rocketmq.RocketMqProperties;
import com.example.consumer.starter.ExplainService;
import com.example.service.GradeService;
import com.example.thread.SyncTaskThread;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.mail.javamail.JavaMailSenderImpl;
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

public class SpringMybatisApplication extends SpringBootServletInitializer implements ApplicationRunner, CommandLineRunner {
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
                        "应用访问连接:\n\t" +
                        "swagger地址: \t\thttp://{}:{}/swagger-ui.html\n\t" +
                        "doc地址: \t\thttp://{}:{}/doc.html\n\t" +
                        "----------------------------------------------------------",
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port"),
                InetAddress.getLocalHost().getHostAddress(),
                environment.getProperty("server.port")
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

    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        // 设置value的序列化规则和 key的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner start . . .");
        explainService.explain("李四","springboot starter。。。");
    }
}
