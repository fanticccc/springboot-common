package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.consumer.starter.ExplainService;
import com.example.pojo.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author： SongJunBao
 * @Date： 2022/9/14 17:53
 * @Description： 测试rabbitmq消息的发送
 * @Version： 1.0
 * @Package: com.example.controller
 */
@RestController
@RequestMapping("rabbitmq")
public class RabbitMqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("send")
    public String sendMsg() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        Message message = new Message(messageId, messageData, createTime);
        String s = JSON.toJSONString(message);
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting",s);
        return "ok";
    }
}
