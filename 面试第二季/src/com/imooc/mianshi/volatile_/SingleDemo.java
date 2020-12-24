package com.imooc.mianshi.volatile_;

/**
 * 单例模式 总计6种  此版本为 DCL+volatile
 */
public class SingleDemo {
    //这里加了volatile是为了禁止指令重排 只有小概率才会出现（99.99%）
    private volatile static SingleDemo instance = null;

    //私有构造方法
    private SingleDemo() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingleDemo()");
    }

    /**
     * DCL double check lock 双重锁定  -- 双端检锁机制
     *
     * @return
     */
    private static SingleDemo getInstance() {
        if (instance == null) {
            //同步代码块  在加锁之前和加锁之后 都进行判断
            //例子 卫生间没人 锁上 然后不放心 再检查下是否没人 (#^.^#)嘻嘻~
            synchronized (SingleDemo.class) {
                if (instance == null) {
                    instance = new SingleDemo();
                    //这个代码可以分成三步
                    //1.分配内存地址 --->分配座位
                    // 2.初始化对象 ---->初始化网线和插座
                    //3.设置instance指向刚刚分配的内存地址 此时instance！=null  对象还没有完成初始化  ---->各种眼睛看过去
                    //2和3 不存在数据依赖性 可能会发生指令重排
                    //当一个线程访问instance不为null的情况下，由于实例可能还没有完成初始化，也就造成了线程安全问题

                }
            }

        }
        return instance;
    }

    public static void main(String[] args) {
//        System.out.println(SingleDemo.getInstance() == SingleDemo.getInstance());
//        System.out.println(SingleDemo.getInstance() == SingleDemo.getInstance());
//        System.out.println(SingleDemo.getInstance() == SingleDemo.getInstance());
//        System.out.println("");
//        System.out.println("");
//        System.out.println("");
//        System.out.println("");
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                SingleDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }


}
