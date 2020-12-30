package com.imooc.mianshi.lock;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\tinvoked sendSMS");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t###invoked sendEmail");
    }

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\tinvoked get()");
            set();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t###invoked set()");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**可重入锁demo
 * 线程可以进入任何一个它已经拥有锁所同步的代码块
 * 也就是说 在一个同步方法里  再访问另一个同步方法 可以自动获取锁
 * t1	invoked sendSMS
 * t1	###invoked sendEmail
 * t2	invoked sendSMS
 * t2	###invoked sendEmail
 */
public class ReentrantLockDemo {
    public static void main(String[] args) {
       Phone phone = new Phone();
//        new Thread(
//                phone::sendSMS
//                , "t1").start();
//
//        new Thread(
//                phone::sendSMS
//                , "t2").start();
        Thread thread1=new Thread(phone,"t3");
        Thread thread2=new Thread(phone,"t4");
        thread1.start();
        thread2.start();
    }
}
