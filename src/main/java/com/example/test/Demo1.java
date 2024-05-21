package com.example.test;

import com.example.utils.ReadProperty;

import java.io.InputStream;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author SongJunBao
 * @Description:
 * @Date 2022/2/17 16:51
 * @Version 1.0
 * com.example.test
 */

public class Demo1 {
    public static void main(String[] args) {
//        Executors.newFixedThreadPool(2);
//        Executors.newCachedThreadPool();
        System.out.println(ReadProperty.get("application","server.port" ));
        //System.out.println(ResourceBundle.getBundle("application").getString("server.port"));
        //InputStream application = Demo1.class.getClassLoader().getResourceAsStream("application");

//        ThreadPoolExecutor executor = new ThreadPoolExecutor();
        //Thread.State
        String application = ResourceBundle.getBundle("application").getString("server.port");

    }
}
