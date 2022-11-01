package com.example.config.qrConfig;

import cn.hutool.extra.qrcode.QrConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

/**
 * @Author: SongJunBao
 * @Date: 2022/10/14 16:38
 * @Package: com.example.config.qrConfig
 * @Description: -二维码配置
 */
@Configuration
public class QRCode {
    @Bean
    public QrConfig qrConfig(){
        QrConfig qr = new QrConfig();
        qr.setBackColor(Color.white.getRGB());
        qr.setForeColor(Color.black.getRGB());
        return qr;
    }
}
