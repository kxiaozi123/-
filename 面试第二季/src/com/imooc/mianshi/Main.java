package com.imooc.mianshi;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发：多个线程去访问同一个资源
 * 并行：多个线程去访问不同的资源
 * 谈谈对volatile的理解：volatile是jvm提供的轻量级同步机制(synchronized)---> 乞丐版的synchronized
 * 有三大特性： 1.保证可见性（当一个线程 修改了主内存的值 必须要（主内存？还是线程本身？去通知）通知其他线程 这个值改变了  ）
 * 2.不保证原子性
 * 3.禁止指令重排
 * 谈谈JMM(java 内存模型 java Memory Model) 大多数 多线程开发需要去遵守的规范
 * 三大特性
 * 1,可见性
 * 2,原子性
 * 3，有序性
 * 硬盘<内存<cpu  中间有个缓存cache
 */
class MyData {
    volatile int num = 0;
    AtomicInteger atomicInteger=new AtomicInteger();


    public void addTo60() {
        this.num = 60;
    }
    //如何保证原子性？
    //使用 AtomicInteger 底层是cas
    public void addPlus() {
        num++;
    }
    public void addAtomic()
    {
        atomicInteger.getAndIncrement();
    }

}

/**
 * volatile保证可见性和不保证demo
 */
public class Main {
    public static void main(String[] args) {
        //seeOkByVolatile();
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int i1 = 1; i1 <= 1000; i1++) {
                    myData.addPlus();
                    myData.addAtomic();
                }
            }, String.valueOf(i)).start();
        }
        //需要等待上面20的线程都计算完成后 再用main 线程取得最后的结果值
        //后台默认两个线程 main 线程和gc线程
        while (Thread.activeCount()>2)
        {
            //礼让 不执行
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t finally number value :"+myData.num);
        System.out.println(Thread.currentThread().getName()+"\t finally number atomicInteger :"+myData.atomicInteger);

    }

    /**
     * volatile可以保证可见性 及时通知其他线程 主物理内存的值已经发生改变了
     * volatile不保证原子性  原子性 就是保证数据的完整和一致性（不可分割，完整性）
     * 出现了丢失写值的情况 写值覆盖
     */
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + "\t come in");
                    //暂停一会线程
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    myData.addTo60();
                    System.out.println(Thread.currentThread().getName() + "\t update value : " + myData.num);
                }
                , "AAA").start();
        while (myData.num == 0) {

        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over,main get num :" + myData.num);
    }
}
