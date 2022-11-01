package com.example.controller;

import com.example.config.rocketmq.RocketMQPublisher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rocketmq")
@Api(value = "测试rocketmq",tags = "测试rocketmq")
public class RocketMqController {
    @Autowired
    private RocketMQPublisher publisher;

    @RequestMapping(path = "send", method = RequestMethod.POST)
    @ApiOperation(value = "测试mq的发送",notes = "测试mq的发送")
    public String sendMq() {
        publisher.send("hello word ");
        return "success !";
    }

    @RequestMapping(path = "send2", method = RequestMethod.POST)
    @ApiOperation(value = "测试mq的发送",notes = "测试mq的发送")
    public String sendMq2() {
        publisher.send2("hello word ");
        return "success !";
    }
}
