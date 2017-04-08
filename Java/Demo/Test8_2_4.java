package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/28
 * Time: 17:36
 */
class Ssssssss {
    private void f(){
        System.out.println("父类");
    }

    public static void main(String[] args) {
        Ssssssss a = new Ssssssss();
        a.f();

    }
}
public class Test8_2_4 extends Ssssssss{

    private void f(){
        System.out.println("子类");
    }
    public static void main(String[] args) {
        Test8_2_4 f = new Test8_2_4();
        f.f();
    }
}
