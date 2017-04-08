package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/21
 * Time: 17:24
 */
public class Vampire {
    public static void main(String[] args) {
        int[] startDigit = new int[4];
        int[] productDigit = new int[4];
        for (int n1 = 10; n1 <= 99; n1++)
            for (int n2 = n1; n2 <= 99; n2++) {
                if ((n1 * n2) % 9 != (n1 + n2) % 9)
                    continue;
                int product = n1 * n2;
                startDigit[0] = n1 / 10;
                startDigit[1] = n1 % 10;
                startDigit[2] = n2 / 10;
                startDigit[3] = n2 % 10;
                productDigit[0] = product / 1000;
                productDigit[1] = product % 1000 / 100;
                productDigit[2] = product % 1000 % 100 / 10;
                productDigit[3] = product % 1000 % 100 % 10;
                int count = 0;
                for (int x = 0; x < 4; x++)
                    for (int y = 0; y < 4; y++) {
                        if (productDigit[x] == startDigit[y]) {
                            count++;
                            productDigit[x] = -1;
                            startDigit[y] = -2;
                            if (count == 4)
                                System.out.println(n1 + " * " + n2 + " : " + product);
                        }

                    }
            }
    }
}
