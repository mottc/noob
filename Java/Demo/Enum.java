package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/26
 * Time: 17:22
 */

public class Enum {
    public static void main(String[] args) {
        for (A s : A.values())
            System.out.println(s + ",ordinal " + s.ordinal());
    }

}
