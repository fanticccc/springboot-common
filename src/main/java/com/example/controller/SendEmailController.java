package com.example.controller;

import com.example.utils.EmailSender;
import com.example.utils.ReadProperty;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
@Slf4j
@Api(value = "测试邮件功能",tags = "测试邮件功能")
public class SendEmailController {

    private final static String TO_EMAIL = "2548533500@qq.com";

    @Autowired
    private EmailSender emailSender;

    @RequestMapping(value = "sendMsg", method = RequestMethod.POST)
    @ApiOperation(value = "发送邮件",notes = "发送邮件")
    public void sendMsg() {
        log.info("send email start ...");
        try {
            emailSender.send("Test msg ", "hello word", TO_EMAIL);
            log.info(Thread.currentThread().getName() + ":邮件发送成功 ！");
        } catch (Exception ex) {
            log.error("邮件发送异常：{}", ex.getMessage());
        }
    }

    @GetMapping("getValue")
    @ApiOperation(value = "获取yml信息",notes = "获取yml信息")
    public String getValue() {
        return ReadProperty.get("country", "name");
    }
}
