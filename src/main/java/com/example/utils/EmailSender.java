package com.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailSender {

    @Autowired
    private JavaMailSenderImpl javaMailSender ;
    /**
     * 发送电子邮件
     * @param subject 标题
     * @param msg 内容
     * @param email 邮箱
     */
    public void send (String subject,String msg , String email){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setText(msg);
        mailMessage.setFrom(email);
        mailMessage.setTo(email);
        javaMailSender.send(mailMessage);
    }
}
