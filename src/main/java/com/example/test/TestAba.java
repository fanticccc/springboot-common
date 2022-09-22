package com.example.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestAba {

    public static void main(String[] args) {
        AtomicStampedReference<Integer> atomic = new AtomicStampedReference<>(1, 1);
        atomic.compareAndSet(1, 2, atomic.getStamp(), atomic.getStamp() + 1);

    }

}
