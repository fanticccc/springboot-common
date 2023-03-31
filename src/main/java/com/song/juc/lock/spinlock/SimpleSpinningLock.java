package com.song.juc.lock.spinlock;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 自旋锁
 */
class SimpleSpinningLock {
    /**
     * 持有锁的线程，null表示锁未被线程持有
     */
    private AtomicReference<Thread> sign = new AtomicReference<>();

    /**
     * 调用lock方法时，如果sign当前值为null，说明自旋锁还没有被占用，将sign设置为currentThread，并进行锁定。
     * 调用lock方法时，如果sign当前值不为null，说明自旋锁已经被其他线程占用，当前线程就会在while中继续循环检测。
     */
    public void lock() {
        //返回的正是执行当前代码指令的线程引用
        Thread currentThread = Thread.currentThread();
        //expect:它指定原子对象应为的值。
        //val:如果原子整数等于期望值，则该值指定要更新的值。
        while (!sign.compareAndSet(null, currentThread)) {
            //当ref为null的时候compareAndSet返回true，反之为false
            //通过循环不断的自旋判断锁是否被其他线程持有
        }
    }

    /**
     * 调用unlock方法时，会将sign置为空，相当于释放自旋锁。
     */
    public void unLock() {
        Thread currentThread = Thread.currentThread();
        //expect:它指定原子对象应为的值。
        //val:如果原子整数等于期望值，则该值指定要更新的值。
        sign.compareAndSet(currentThread, null);
    }
    
    /*static int change(int milk ,int boulte,int gai){
//        if (boulte ==0 && gai==0){
//            return
//        }

        milk = boulte/3 + gai/5 + milk ;
        boulte = boulte % 3 ;
        gai = gai % 5;
        if ( boulte< 3 && gai <5){
            return milk;
        }
        return change(gai,boulte,milk);
    }
*/


}

