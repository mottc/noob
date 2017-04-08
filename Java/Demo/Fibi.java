package com.practice;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/28
 * Time: 14:06
 */
class FibiWay{
    static int Fibin(int i){
        if (i == 1 || i == 2){
            return 1;
        }
        else{
            return Fibin(i - 1) + Fibin(i - 2);
        }
    }

}
public class Fibi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        for (int n = 1; n <= i; n ++)
        System.out.println(n + "---->" +FibiWay.Fibin(n));

    }
}
