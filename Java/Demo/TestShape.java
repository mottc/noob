package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/28
 * Time: 16:41
 */
class Shape{
    public void print(){
        System.out.println("父类方法");
    }
    public void print1(){
        System.out.println("父类方法1");
    }

}
class Cycle1 extends Shape{
    @Override
    public void print(){
        System.out.println("子类方法");
    }
    public void print2(){
        System.out.println("子类方法2");
    }

}
public class TestShape {
    public static void main(String[] args) {
        Shape a = new Cycle1();
        a.print();
        a.print1();
        ((Cycle1)a).print2();
    }
}
