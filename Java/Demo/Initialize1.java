package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/25
 * Time: 15:51
 */
public class Initialize1 {
    public String a = "aaaa";
    public String b;
    public Initialize1(String q){
        b = q;
    }

    public static void main(String[] args) {
        Initialize1 w = new Initialize1("bbbb");
        System.out.println(w.a);
        System.out.println(w.b);
    }
}
