package com.practice;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/25
 * Time: 11:37
 */
public class Input {
    public static void main(String[] args) {
        System.out.println("请输入一个数：");
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        System.out.println("输入的数是：" + num);

    }
}
