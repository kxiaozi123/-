package com.imooc.mianshi.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * CAS 比较并交换  ---> compareAndSet  有点类似github 版本号
 * cas 它是一条cpu并发原语 在执行过程中不允许被中断
 * 不会造成数据不一致问题
 * cas底层原理 靠的是unsafe类rt.jar sum.mic
 * valueoffset 表示 内存中的偏移地址
 * var5 = this.getIntVolatile(var1, var2); 是获取当前的最新值
 * CAS思想（自旋）
 * 比较当前工作内存的值和主内存的值 如果相同 执行操作，否则继续比较
 * 主内存和工作内存的值一致为止
 * cas缺点：
 * 1.循环时间长 开销大  如果一直不成功 可能会给cpu带来很大的开销
 * 2.从数量上只能保证一个共享变量
 * 3.会有ABA问题
 *什么是ABA ----->狸猫换太子
 *
 * 尽管线程one的CAS操作成功，但是不代表这个过程就是没有问题的。
 * //----
 *
 */
public class CASDemo {
    public static void main1(String[] args) {
        AtomicInteger integer=new AtomicInteger(5);
        System.out.println(integer.compareAndSet(5, 2019)+"\t current data:"+integer.get());
        System.out.println(integer.compareAndSet(5, 1024)+"\t current data:"+integer.get());
        integer.getAndIncrement();

    }

    public static void main(String[] args) {
        List<String> s1=new ArrayList<>();
        s1.add("a");
        s1.add("b");
        List<String> s2=new ArrayList<>();
        s2.add("c");
        s2.add("d");
        s2.add("d");
        s1.addAll(s2);
        System.out.println(s1);
        List<String> list= s1.stream().distinct().collect(Collectors.toList());
        System.out.println(list);
    }
}
