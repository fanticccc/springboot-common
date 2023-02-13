package com.example.controller;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Author: SongJunBao@pukkasoft.cn
 * @Date: 2022/12/15 17:18
 * @Package: com.example.controller
 * @Description: -TODO
 */
@RequestMapping("/zip")
@Slf4j
@RestController
public class TestZipFileController {



    /**
     * 处理文件
     * @param files
     */
    @RequestMapping(method = RequestMethod.POST,value = "uploadList")
    public void dealFiles(MultipartFile[] files) throws RuntimeException, IOException {
        //MultipartFile[] files = dto.getFiles();
        if  (ArrayUtil.isEmpty(files)){
            log.error("未上传文件！");
            return;
        }

        List<File> tmpFiles = Lists.newArrayListWithExpectedSize(files.length);
        // TODO
        String filePathPrefix = "D:\\";
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

//            String extName = cn.hutool.core.io.FileUtil.extName(file.getOriginalFilename());
//            if (Strings.isNullOrEmpty(extName)) {
//                extName = ".zip";
//            }
            String filePath = filePathPrefix + file.getOriginalFilename();
            File _file = new File(filePath);
            file.transferTo(_file);
            tmpFiles.add(_file);
        }
//        List<MultipartFile> files = Arrays.asList(files);
//        List<File> resourceFiles = files.stream().map(file -> {
//            InputStream ins = null;
//            try {
//                ins = file.getInputStream();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            // 创建零时文件
//            File f = new File(Objects.requireNonNull(file.getOriginalFilename()));
//            // MultipartFile 转 File
//            FileUtil.inputStreamToFile(ins, f);
//            return f;
//        }).collect(Collectors.toList());
        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setEncryptFiles(true);
        zipParameters.setEncryptionMethod(EncryptionMethod.AES);

        // 压缩后的文件夹名称
        String zipName = "D:\\special".concat(".zip");
        //创建zip文件
        String[] param = generateParams();

        try (ZipFile zipFile = new ZipFile(zipName, param[3].toCharArray())) {
            // file 转 zipFile
            zipFile.addFiles(tmpFiles, zipParameters);
        }
        for (File tmpFile : tmpFiles) {
            System.out.println("删除文件："+tmpFile.getName());
            tmpFile.delete();
        }
    }

    private String[] generateParams(){
        String code = IdUtil.fastSimpleUUID();
        String workOrderNo = IdUtil.fastSimpleUUID();
        String password = code + workOrderNo;
        return  new String[]{code,workOrderNo,password};
    }
}
