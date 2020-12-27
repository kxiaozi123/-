package com.imooc.mianshi.ABADemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABAResolve {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {
        //ABA问题的产生
       /* new Thread(
                () -> {
                    atomicReference.compareAndSet(100, 101);
                    atomicReference.compareAndSet(101, 100);

                }
                , "t1").start();

        new Thread(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(atomicReference.compareAndSet(100, 2019));
                    System.out.println(atomicReference.get());
                }
                , "t2").start();*/

        //ABA问题的解决  原子时间戳引用(类似于版本号)
        new Thread(
                () -> {
                    int stamp = atomicStampedReference.getStamp();
                    System.out.println(Thread.currentThread().getName()+"第一次"+stamp);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                    System.out.println(Thread.currentThread().getName()+"第二次"+atomicStampedReference.getStamp());
                    atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                    System.out.println(Thread.currentThread().getName()+"第三次"+atomicStampedReference.getStamp());

                }
                , "t3").start();


        new Thread(
                () -> {
                    int stamp = atomicStampedReference.getStamp();
                    System.out.println(Thread.currentThread().getName()+"第一次"+stamp);
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1));
                    System.out.println(Thread.currentThread().getName()+"当前最新版本号"+atomicStampedReference.getStamp());
                    System.out.println(Thread.currentThread().getName()+"当前最新值"+atomicStampedReference.getReference());

                }
                , "t4").start();
    }
}
