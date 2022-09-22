package com.example.test.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Callable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableThread callable = new CallableThread();
        FutureTask futureTask = new FutureTask<String>(callable);
        new Thread(futureTask, "线程ca").start();
        String o = (String) futureTask.get();
        System.out.println(o);
        System.out.println(Thread.currentThread().getName() + "正在执行。。。");
    }
}

/**
 * 传入的泛型 即为返回值
 */
class CallableThread implements java.util.concurrent.Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "在执行。。。");
        return "aaa";
    }
}
