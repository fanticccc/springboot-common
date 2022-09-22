package com.example.test.thread.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        int i1 = Runtime.getRuntime().availableProcessors();
        System.out.println(i1);
        CountDownLatch countDownLatch = new java.util.concurrent.CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "--aaa");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--aaa");
            try {
                TimeUnit.SECONDS.sleep(5);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }, "线程A").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--aaa");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }, "线程B").start();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "--aaa");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }, "线程C").start();
        countDownLatch.await();
        System.out.println("All Thread run over ...");

    }
}
