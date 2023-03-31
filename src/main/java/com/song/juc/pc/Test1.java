package com.song.juc.pc;

public class Test1 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    ticket.inr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    ticket.dir();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

    }

}

class Ticket {
    private int number = 0;

    public synchronized void inr() throws InterruptedException {
        if (number != 0) {
            // 等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "-->shengcan-->" + number);
        this.notifyAll();
    }

    public synchronized void dir() throws InterruptedException {
        if (number == 0) {
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "-->xiaof-->" + number);
        this.notifyAll();
    }
}
