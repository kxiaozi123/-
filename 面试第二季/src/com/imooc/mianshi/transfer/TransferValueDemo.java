package com.imooc.mianshi.transfer;
class Person{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }
}

/**
 * 提神醒脑小题目
 */
public class TransferValueDemo {
    public void changeValue1(int age)
    {
        age=30;
    }
    public void changeValue2(Person person)
    {
        person.setName("xxxx");
    }
    public void changeValue3(String str)
    {
       str="xxxx";
    }

    public static void main(String[] args) {
        TransferValueDemo demo=new TransferValueDemo();
        int age=20;
        demo.changeValue1(age);
        System.out.println(age);
        Person person=new Person("abc");
        demo.changeValue2(person);
        System.out.println(person.getName());
        String str="abc";
        demo.changeValue3(str);//会去字符串常量池里检查 有没有这个值
        System.out.println(str);
    }
}
