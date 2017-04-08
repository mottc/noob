package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/26
 * Time: 14:20
 */
public class MethodInAnotherClass {
    public void say(){
        System.out.println("Say!");
    }
    public void hello(){
        System.out.println("hello!");
        say();
    }

    public static void main(String[] args) {
        MethodInAnotherClass a = new MethodInAnotherClass();
        Speed b = new Speed();
        a.hello();
        System.out.println(b.speed(15,3));

    }
}
