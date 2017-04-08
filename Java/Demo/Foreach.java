package com.practice;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/21
 * Time: 16:54
 */
public class Foreach {
    public static void main(String[] args) {
        Random rank = new Random(47);
        float f[] = new float[10];
        for (int i = 0;i < 10;i++)
            f[i] = rank.nextFloat();
        for (float x : f)
            System.out.println(x);
    }
}
