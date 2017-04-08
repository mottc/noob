package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/26
 * Time: 16:30
 */
public class Init_5_14 {
    static String a = "aaa";
    static String b;
    static {
        b = "bbb";
    }
    static void print(){
        System.out.println(a);
        System.out.println(b);
    }

    public static void main(String[] args) {
        Init_5_14 a = new Init_5_14();
        Init_5_14.print();
    }
}
