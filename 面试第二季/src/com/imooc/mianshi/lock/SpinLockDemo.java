package com.imooc.mianshi.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 指的是 尝试获得锁的线程不会立即阻塞
 * 而是采取循环的方式去获得锁 生活case：老师打电话 你不站那一直耗着 而是多次去看看 去询问
 * 这样的好处 是减少 上下文的切换 缺点是会消耗CPU
 * <p>
 * 实现一个自旋锁
 *
 * 好处 不需要阻塞 坏处 消耗cpu 性能下降
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t come in O(∩_∩)O~");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t invoke myUnLock O(∩_∩)O~");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(
                () -> {
                    spinLockDemo.myLock();
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    spinLockDemo.myUnLock();
                }
                , "AA").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(
                () -> {
                    spinLockDemo.myLock();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    spinLockDemo.myUnLock();

                }
                , "BB").start();
    }

}
