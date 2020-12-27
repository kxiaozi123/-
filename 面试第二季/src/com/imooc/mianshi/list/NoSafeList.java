package com.imooc.mianshi.list;



import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全的问题
 * 出现的问题：
 * ConcurrentModificationException
 * 产生的问题：
 * 并发争抢修改导致的， 参考我们签到的情况
 * 一个人正在写入，另一个同学过来抢夺，导致数据不一致异常。并发修改异常。
 * 如何解决：
 * 1.vector
 * 2.Collections.synchronizedList(new ArrayList<>())
 * 3.CopyOnWriteArrayList
 * 写时复制  读写分离的思想  还没写完的时候 是原来的（允许一起读）  写完了 就更新（写时加锁）
 *
 */
public class NoSafeList {
    public static void main(String[] args) {
        //List<String> list=new ArrayList<>();
        Set<String> set=new CopyOnWriteArraySet<>();
        //实际上 是一个空的CopyOnWriteArrayList
        //listNotSafe();
        //new HashSet<>().add("a");
        //hashSet 底层hashMap  hashset的add方法 调用hashMap的put方法 k是element v是present的常量
        Map<String,String> map=new ConcurrentHashMap<>();
        //HashMap和concurrentHashMap区别？1.7分段锁和锁优化

    }

    private static void listNotSafe() {
        List<String> list=new CopyOnWriteArrayList<>();
        //List<String> list= Collections.synchronizedList(new ArrayList<>());
//        list.forEach(System.out::print);
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
