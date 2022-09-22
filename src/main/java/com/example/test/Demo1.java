package com.example.test;

import lombok.SneakyThrows;

/**
 * @Author song
 * @Description:
 * @Date 2022/2/17 16:51
 * @Version 1.0
 * com.example.test
 */

public class Demo1 {
    public static void main(String[] args) {
        System.out.println("aaa");
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(1000);
                    System.out.print("bbb、");
                }
            }
        }).start();
    }
}
