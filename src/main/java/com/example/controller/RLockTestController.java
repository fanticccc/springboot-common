package com.example.controller;

import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: SongJunBao@pukkasoft.cn
 * @Date: 2023/6/29 3:02
 * @Package: com.example.controller
 * @Description: -TODO
 */
@RestController
@RequestMapping("lock")
public class RLockTestController {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @PostMapping("sell")
    public String sellGoods(){


        return "ok" ;
    }

    private void lock(String code){

    }
    private void unLock(){

    }

}
