package com.example.config.qrConfig;

import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @Author: SongJunBao
 * @Date: 2022/10/14 16:43
 * @Package: com.example.config.qrConfig
 * @Description: -操作二维码
 */
@Service
public class QrService {

    @Resource
    private QrConfig qrConfig;

    @Value("${system-parameters.qrcode_path}")
    private String qr_path ;

    public void  generateFile(String content){
        // 生成到本地文件
        //QrCodeUtil.generate(content,qrConfig,new File(qr_path +"/"+ DateUtil.now()));
        QrCodeUtil.generate(content,qrConfig,new File(qr_path));
    }
    // 输出到流
    public void generateStream(String content, HttpServletResponse response) throws IOException {
        generateFile(content);

        QrCodeUtil.generate(content,qrConfig,"png",response.getOutputStream());
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.now());
        System.out.println(DateUtil.currentSeconds());
    }
}
