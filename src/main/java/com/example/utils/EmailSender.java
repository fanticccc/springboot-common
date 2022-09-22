package com.example.utils;

import com.example.config.email.EmailProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import sun.nio.ch.ThreadPool;

import java.util.ResourceBundle;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Configuration
@EnableConfigurationProperties(EmailProperties.class)
//@EnableAsync //开启多线程
public class EmailSender {
    private JavaMailSender javaMailSender;

    private EmailProperties emailProperties;

    @Autowired
    public void setEmailProperties(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
    }

    @Autowired
    @Qualifier("adminThreadPool")
    private ThreadPoolExecutor executor;

    private void initMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(emailProperties.getUsername());
        javaMailSender.setPassword(emailProperties.getPassword());
        javaMailSender.setHost(emailProperties.getHost());
        javaMailSender.setProtocol(emailProperties.getProtocol());
        javaMailSender.setDefaultEncoding(emailProperties.getDefaultEncoding());
        this.javaMailSender = javaMailSender;
    }

    /**
     * 发送电子邮件
     *
     * @param subject 标题
     * @param msg     内容
     * @param email   邮箱
     */
    public void send(String subject, String msg, String email) {
        initMailSender();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(subject);
        mailMessage.setText(msg);
        mailMessage.setFrom(email);
        mailMessage.setTo(email);
        Runnable sendEmail = () -> javaMailSender.send(mailMessage);
        executor.submit(sendEmail);
        log.info("executor do sync task ..." + Thread.currentThread().getName());
    }
}
