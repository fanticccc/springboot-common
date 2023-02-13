package com.example.controller;

import cn.hutool.core.util.ZipUtil;
import com.example.utils.FileUtil;
import com.example.utils.NETFunction;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Author: SongJunBao@pukkasoft.cn
 * @Date: 2022/12/13 16:16
 * @Package: com.example.controller
 * @Description: -测试文件压缩
 */
@RestController
@RequestMapping("zip")
@Slf4j
public class ZipFileController {

    private static final String ZIP_FILE_URL = "C:\\Users\\64765\\Desktop\\test.zip";

    private static final int BUFFER_SIZE = 2 * 1024 ;

    @RequestMapping(method = RequestMethod.POST,value = "upload")
    public void zipFile(MultipartFile file)  {
        if (file == null){
            throw new RuntimeException("【ZipFileController】- Method-zipFile 上传文件为空！");
        }
        FileOutputStream fos = null;
        ZipOutputStream zos = null;
        File f = null ;
        try{
            InputStream ins = file.getInputStream();
            f = new File(file.getOriginalFilename());
            FileUtil.inputStreamToFile(ins, f);
            fos = new FileOutputStream(ZIP_FILE_URL);
            zos = new ZipOutputStream(fos);
            byte[] buf = new byte[BUFFER_SIZE];
            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);
            int len;
            FileInputStream inputStream = new FileInputStream(f);
            while ((len = inputStream.read(buf)) != -1){
                zos.write(buf,0,len);
            }

        }catch (Exception ex){
            log.error("resolve file Exception:{}",ex);
        }finally {
            NETFunction.closeStream(zos);
            NETFunction.closeStream(fos);
        }
    }
}
