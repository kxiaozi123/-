package com.imooc.mianshi.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS 比较并交换  ---> compareAndSet  有点类似github 版本号
 * cas 它是一条cpu并发原语 在执行过程中不允许被中断
 * 不会造成数据不一致问题
 * cas底层原理 靠的是unsafe类rt.jar sum.mic
 * valueoffset 表示 内存中的偏移地址
 *
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger integer=new AtomicInteger(5);
        System.out.println(integer.compareAndSet(5, 2019)+"\t current data:"+integer.get());
        System.out.println(integer.compareAndSet(5, 1024)+"\t current data:"+integer.get());
        integer.getAndIncrement();

    }
}
