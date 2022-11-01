package com.example.config.qrConfig;

import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public void  generateFile(String content,File file){
        // 生成到本地文件
        QrCodeUtil.generate(content,qrConfig,file);
    }
    // 输出到流
    public void generateStream(String content, HttpServletResponse response) throws IOException {
        QrCodeUtil.generate(content,qrConfig,"png",response.getOutputStream());
    }
}
