package com.example.utils;
import lombok.extern.slf4j.Slf4j;
/**
 * @Author: SongJunBao@pukkasoft.cn
 * @Date: 2022/12/13 16:16
 * @Package: com.example.controller
 * @Description: -统一的资源关闭方法（可用于（直接或间接）继承或实现AutoCloseable的资源）
 */
@Slf4j
public class NETFunction {
    public static <T extends AutoCloseable> void closeStream(T t){
        if (t != null){
            try {
                t.close();
            }catch (Exception e){
                log.error("资源关闭失败：{}",e.getMessage());
            }
        }
    }
}