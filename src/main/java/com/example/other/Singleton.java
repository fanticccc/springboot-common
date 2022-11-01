package com.example.other;

/**
 * @Author SongJunBao
 * @Description: 单例模式
 * @Date 2022/2/16 17:40
 * @Version 1.0
 * com.example.utils
 */


public class Singleton {
    /*//饿汉式
    private static Singleton instance = new Singleton();
    private Singleton(){}
    private Singleton getInstance(){
        return instance;
    }*/
//----------------------------------------
    /* //懒汉式
     private static Singleton instance = null;
     private Singleton(){}
     public Singleton getInstance(){
         if (instance==null){
             instance = new Singleton();
         }
         return instance;
     }*/
//----------------------------------------------
    //双重锁（线程安全）
    private static Singleton instance = null;

    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
