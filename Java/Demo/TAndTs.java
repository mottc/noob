package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/8/4
 * Time: 9:39
 */
class Person {
    private int age;
    public void setAge(int age) throws Exception{
        if (age < 0){
            throw new Exception("年龄不能为负！");
        }
        this.age = age;
    }
}
public class TAndTs {
    public static void main(String[] args) {
        Person person = new Person();
        try {
            person.setAge(-20);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
