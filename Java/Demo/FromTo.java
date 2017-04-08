package com.practice;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/22
 * Time: 14:11
 */
public class FromTo {
    int begin;
    int end;
    public FromTo(int a,int b){
        for (int i = a; i <= b; i++){
            System.out.print(i + " ");
        }
    }
    public static void input(){
        Scanner in = new Scanner(System.in);
        System.out.println("请输入数列开始数字：");
        int b = in.nextInt();
        System.out.println("请输入数列结束数字：");
        int e = in.nextInt();
        if (b <= e){
            FromTo demo = new FromTo(b,e);
        }
        else {
            System.out.println("输入有误，请重新输入！");
            input();  //递归
        }
    }

    public static void main(String[] args) {
        input();
    }
}
