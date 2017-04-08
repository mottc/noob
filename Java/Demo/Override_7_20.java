package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/28
 * Time: 11:12
 */
class Fi{
    private void print(){
        System.out.println("Hello World!");
    }
}
public class Override_7_20 extends Fi{
    //@Override
    private void print(){
        System.out.println("2");
    }

    public static void main(String[] args) {
        Override_7_20 a = new Override_7_20();
        a.print();
    }
}
