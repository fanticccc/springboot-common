package com.example.config.email;

import lombok.Data;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Data
@Component
@ConfigurationProperties("spring.mail")
public class EmailProperties {
    private String username;
    private String password;
    private String host;
    private Integer port;
    private String defaultEncoding = "utf-8";
    private String protocol = "smtps";
    private Properties properties;
}
