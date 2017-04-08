package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/27
 * Time: 16:35
 */
class AAA {

    int a(int i, int n) {
        System.out.println("AAA.a()");
        return 1;

    }
}

public class OverRide extends AAA {
    public static void main(String[] args) {
        OverRide a = new OverRide();
        a.a(1, 2);
    }

    @Override
    int a(int ui, int lp) {
        System.out.println("OverRide.a()");
        return 2;

    }
}
