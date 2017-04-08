package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/25
 * Time: 11:07
 */
public class SuShu {

    public void Ditecyive() {
        int t = 0;

        int i = 0;
        for (i = 1; i < 100; i++) {
            int m = 0;
            if (i == 1){
                System.out.println("1是素数");
                continue;
            }
            for (int s = 1; s <= i; s++) {
                t = i % s;
                if (t == 0)
                    m = m + 1;
            }
            if (m == 2) {
                System.out.println(i + "是素数");

            } else {
                System.out.println(i + "不是素数");

            }

        }
    }

    public static void main(String[] args) {
        SuShu a = new SuShu();
        a.Ditecyive();

    }
}