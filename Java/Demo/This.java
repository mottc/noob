package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/25
 * Time: 16:52
 */
public class This {
    public void first(){
        second();
        this.second();
    }
    public void second(){
        System.out.println("+++");
    }

    public static void main(String[] args) {
        This a = new This();
        a.first();
    }
}
