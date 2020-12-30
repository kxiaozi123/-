package com.imooc.mianshi.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class myCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock lock=new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在写入:" + key);
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成:");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }


    }

    public void get(String key) {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t正在读取:");
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成:" + result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
    public void clear()
    {
        map.clear();
    }
}

/**
 * 读写锁demo
 * 独占锁（写锁）：指的是该锁只能被一个线程持有 例如synchronize和ReentrantLock 就是独占锁
 * <p>
 * 共享锁(读锁):该锁可以被一个线程锁持有
 * <p>
 * ReentrantReadWriteLock 读锁是共享锁 写锁是独占锁
 * 读写 写读 写写是互斥的  读-读共存 读-写 不能共存 写-写不能共存
 * 类似于 航空公司的日程表 多个人读 只允许一个人改
 * <p>
 * <p>
 * 写操作：原子+独占
 */
public class ReadAndWriteLockDemo {
    public static void main(String[] args) {
        myCache myCache = new myCache();
        for (int i = 1; i <= 5; i++) {
            final int finalI = i;
            new Thread(() -> {
                myCache.put(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int finalI = i;
            new Thread(() -> {
                myCache.get(finalI+"");
            }, String.valueOf(i)).start();
        }
    }
}
