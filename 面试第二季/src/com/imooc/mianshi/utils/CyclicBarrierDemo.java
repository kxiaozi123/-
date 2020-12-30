package com.imooc.mianshi.utils;

import java.util.concurrent.CyclicBarrier;

/**
 * 做加法 人到齐了才可以开会 集齐7个龙珠召唤神龙
 *
 * 先到的先等 等人齐了开打
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,
                () -> System.out.println("********召唤神龙"));
        for (int i = 1; i <= 7; i++) {
            final int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t收集到第"+ finalI +"龙珠");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }

}
