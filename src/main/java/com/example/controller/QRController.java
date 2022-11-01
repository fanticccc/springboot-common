package com.example.controller;

import com.example.config.qrConfig.QrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @Author: SongJunBao@pukkasoft.cn
 * @Date: 2022/10/14 16:50
 * @Package: com.example.controller
 * @Description: -测试生成二维码
 */
@Controller
@RequestMapping("qr")
@Api(value = "二维码操作",tags = "二维码操作")
public class QRController {
    @Autowired
    private QrService qrService;
    @GetMapping("code")
    @ApiOperation(value = "生成二维码",notes = "生成二维码")
    public String getQrCode(@RequestParam(value = "message") String message, HttpServletResponse response) throws IOException {
        qrService.generateStream(message,response);
        return "ok";
    }

}
