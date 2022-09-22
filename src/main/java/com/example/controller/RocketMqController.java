package com.example.controller;

import com.example.config.rocketmq.RocketMQPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rocketmq")
public class RocketMqController {
    @Autowired
    private RocketMQPublisher publisher;

    @RequestMapping(path = "send", method = RequestMethod.POST)
    public String sendMq() {
        publisher.send("hello word ");
        return "success !";
    }
}
