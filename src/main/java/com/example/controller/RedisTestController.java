package com.example.controller;

import com.example.utils.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author SongJunBao
 * @Description: 测试redis
 * @Date 2022/5/17 17:50
 * @Version 1.0
 * com.example.controller
 */
@Slf4j
@RestController
@RequestMapping("redis")
@Api(value = "redis功能测试",tags = "redis功能测试")

public class RedisTestController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @GetMapping("/setData")
    @ApiOperation(value = "redis存储数据",notes = "redis存储数据")
    public void setRedisData() {
        String msg = "==Hello word==";
        redisUtils.set("redisKey", msg);
        System.out.println("存入数据：" + msg);
    }

    @GetMapping("/setData1")
    public void setRedisData1() {
        redisUtils.set("code", "1235qea2-dad");
        System.out.println("存入数据：" + "Hello word");
    }
    @ApiOperation(value = "获取redis数据",notes = "获取redis数据")
    @GetMapping("/getData1")
    public void getRedisData1() {
        Object redisData = redisUtils.get("redisKey");
        System.out.println("Redis中获取到的数据：" + redisData);
    }

    @GetMapping("/getData2")
    public void getRedisData2() {
        Object redisData = redisUtils.get("code");
        System.out.println("Redis中获取到的数据：" + redisData);
    }

    @GetMapping("/getData3")
    public void getRedisData3() {
        Object redisData = redisTemplate.opsForValue().get("code");
        System.out.println("Redis中获取到的数据：" + redisData);
    }

    @PostMapping("expire")
    public void setExpireTime() {
        boolean code = redisUtils.exists("code");
        Boolean code1 = redisUtils.expire("code", 5000, TimeUnit.MILLISECONDS);
        System.out.println(code);
        System.out.println(code1);
    }

}
