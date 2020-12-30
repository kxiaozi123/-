package com.imooc.mianshi.utils;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * 类似于倒计时 秦灭六国一统华夏 减法
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        //closeDoor();
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t国，被灭");
                countDownLatch.countDown();
            }, Objects.requireNonNull(CountryEnum.forEach(i)).getMsg()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t******秦帝国，一统华夏");

    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t上完自习离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t******班长最后关门走人");
    }
}
