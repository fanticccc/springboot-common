package com.example.test;

/**
 * 线程安全的单例模式（懒汉式 ）
 */
public class Singleton {

    private Singleton() {
    }

    private static Singleton singleton;

    public static Singleton getInstance() {

        if (singleton == null) {
            synchronized (Singleton.class) {
                singleton = new Singleton();
            }
        }
        return singleton;
    }
}
