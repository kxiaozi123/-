package com.imooc.mianshi.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁 和非公平锁
 * 公平：多个线程按照申请锁的顺序 类似于打饭 先来后到
 * 非公平：多个线程不按照申请锁的顺序 有可能 比之前先申请的线程先得到锁 有可能造成优先级反转或者饥饿现象
 * 如果失败 就采用类似公平锁的方式
 * 好处：优点在于吞吐量比公平锁大
 * synchronized 也是非公平锁
 *
 *
 * 可重入锁（递归锁）： 上厕所的例子
 * 线程可以进入任何一个它已经拥有锁所同步的代码块
 * 生活例子： 大门上锁 厨房和卫生间就不需要上锁
 * 最大的特点：避免死锁
 */
public class LockDemo {
    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
    }
}
