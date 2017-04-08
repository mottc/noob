package com.practice;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/25
 * Time: 12:41
 */
public class Test1 {


        public static void Ditecyive(int num){
            int t = 0;

            for (t = 2;t < num;t++) {
                int m = num % t;
                if (m == 0) {
                    System.out.println(num + "不是素数");
                    break;
                }
            }
            if (t == (num-1)||t == num)
                System.out.println(num + "是素数");
        }
        public static void main(String[] args) {
            System.out.println("请输入一个小于100的整数：");
            Scanner in = new Scanner(System.in);
            int num = in.nextInt();
            Test1 a = new Test1();
            a.Ditecyive(num);
        }

}
