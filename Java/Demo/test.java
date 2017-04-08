package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/22
 * Time: 14:53
 */
public class test {
    Outer w = new Outer("a");
    Outer.Inner d = w.new Inner();
    public static void main(String[] args) {
        Speed a = new Speed();
        System.out.println(a.speed(30,5));
    }

}
