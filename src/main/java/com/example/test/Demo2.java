package com.example.test;

/**
 * @Author YSTen_SongJunBao
 * @Description:
 * @Date 2022/2/17 16:59
 * @Version 1.0
 * com.example.test
 */

public class Demo2 {
    public static void main(String[] args) {
        new Thread(()->System.out.println("aaa")).start();
    }
}
