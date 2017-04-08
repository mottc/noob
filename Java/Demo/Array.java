package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/26
 * Time: 16:55
 */
public class Array {
    Array(String s){
        System.out.println("k" + s);
    }

    public static void main(String[] args) {
        Array[] a = new Array[10];
        a[0] = new Array("000");
    }
}
