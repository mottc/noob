package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/25
 * Time: 16:29
 */
public class Constructor {
    public Constructor() {
        System.out.println("A");
    }

    Constructor(String str) {
        System.out.println("A:" + str);
    }

    public static void main(String[] args) {
        Constructor a = new Constructor();
        Constructor b = new Constructor("aaa");

    }
}
