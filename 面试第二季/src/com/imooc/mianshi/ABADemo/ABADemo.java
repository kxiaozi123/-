package com.imooc.mianshi.ABADemo;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用demo----ABA问题
 * 原子引用+版本号（类似于版本号）
 *
 */
class User{
    String userName;
    int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
public class ABADemo {
    public static void main(String[] args) {
        User user=new User("z3",22);
        User user2=new User("lisi",25);
        AtomicReference<User> userAtomicReference=new AtomicReference<>();
        userAtomicReference.set(user);
        userAtomicReference.compareAndSet(user,user2);
        System.out.println(userAtomicReference.compareAndSet(user, user2));
        System.out.println(userAtomicReference.get());
    }
}
