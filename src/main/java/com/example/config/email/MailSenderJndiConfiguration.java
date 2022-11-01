//package com.example.config.email;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnJndi;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.autoconfigure.mail.MailProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import javax.mail.Session;
//
///**
// * @Author SongJunBao
// * @Description:
// * @Date 2022/5/5 11:44
// * @Version 1.0
// * com.example.config.email
// */
//@ConditionalOnJndi
//public class MailSenderJndiConfiguration {
//
//    private  MailProperties properties ;
//
//
//    MailSenderJndiConfiguration(MailProperties mailProperties) {
//        this.properties = mailProperties;
//    }
//
//    /*@Bean
//    public JavaMailSenderImpl mailSender(Session session){
//        JavaMailSenderImpl sender = new JavaMailSenderImpl();
//        sender.setDefaultEncoding(this.properties.getDefaultEncoding().name());
//        sender.setSession(session);
//        return sender ;
//    }*/
//   /* @Bean
//    @ConditionalOnMissingBean({ JavaMailSender.class})
//    JavaMailSenderImpl mailSender(MailProperties mailProperties){
//        JavaMailSenderImpl sender = new JavaMailSenderImpl();
//        sender.setDefaultEncoding(this.properties.getDefaultEncoding().name());
//        return sender ;
//    }*/
//}
