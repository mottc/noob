package com.practice;

public class Practice2_8 {
    static int i = 18;

    public static void main(String[] args) {
        Practice2_8 a = new Practice2_8();
        Practice2_8 b = new Practice2_8();
        System.out.println(a.i +"=="+b.i);
        a.i++;
        System.out.println(a.i +"=="+b.i);
    }

}
