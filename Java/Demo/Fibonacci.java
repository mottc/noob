package com.practice;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/21
 * Time: 17:07
 */
public class Fibonacci {
   static int fib(int n){
        if(n <= 2)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        System.out.println("请输入要输出的斐波那契数列的位数：");
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        for (int j = 1;j <= i;j ++){
            System.out.print(Fibonacci.fib(j) +" ");
        }
    }
}
