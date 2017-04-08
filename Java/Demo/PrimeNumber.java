package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/21
 * Time: 16:33
 */
public class PrimeNumber {
    public static void main(String[] args) {
        int max = 1000;
        for (int i = 1;i <= max;i++){
            boolean prime = true;
            for (int j = 2;j < i;j++){
                if (i%j==0)
                    prime = false;
            }
            if (prime == true){
                System.out.println(i+" ");
            }
        }
    }
}
