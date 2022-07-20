package com.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Author song
 * @Description: 读取配置文件
 * @Date 2022/5/17 14:25
 * @Version 1.0
 * com.example.utils
 */
@Slf4j
public class ReadProperty {

    private static ReadProperty dbConfig = new ReadProperty();

    private static Properties properties = new Properties();

    public static ReadProperty getInstance(){
        return dbConfig;
    }
    private void loadProperties(){
        InputStreamReader in = null;
        try{
            in = new InputStreamReader(ReadProperty.class.getResourceAsStream("/application.properties"),"UTF-8");
            properties.load(in);
        }catch (Exception ex){
            log.error("load application  configuration exception", ex);
        }finally {
            try {
                in.close();
            }catch (Exception e){
                log.error("IO exception", e);
            }
        }
    }
    public static String getProperty(String propertiesName){

        if (properties.isEmpty()){
            ReadProperty.getInstance().loadProperties();
        }
        return properties.getProperty(propertiesName);
    }
    public static String get(String propertiesName){
        return getProperty(propertiesName);
    }
}
