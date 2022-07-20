package com.example.config.email;

import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.Session;

/**
 * @Author YSTen_SongJunBao
 * @Description:
 * @Date 2022/5/5 11:38
 * @Version 1.0
 * com.example.config
 */
@Configuration
@Import({MailSenderJndiConfiguration.class,MailProperties.class})
public class MailSenderAutoConfiguration {
}
