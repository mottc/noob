package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/25
 * Time: 17:07
 */
public class ThisInConstructor {
    ThisInConstructor(int i,float j){
        this(i);
        System.out.println(j);
    }
    ThisInConstructor(int i){
        System.out.println(i);
    }

    public static void main(String[] args) {
        ThisInConstructor a = new ThisInConstructor(5);
        ThisInConstructor b = new ThisInConstructor(5,5.3f);
    }
}
