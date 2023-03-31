package com.song.juc.lock.spinlock;

import java.util.concurrent.TimeUnit;

public class VolatileTest {

    private static int num = 1;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (num == 1) {
            }
        }).start();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(num);
        num = 2;
        System.out.println(num);
    }
}
