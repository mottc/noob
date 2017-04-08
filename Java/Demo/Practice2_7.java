package com.practice;

class StaticTest{
    static int i = 47;
}
public class Practice2_7 {
    static void increment(){
        StaticTest.i ++;
    }

    public static void main(String[] args) {
        Practice2_7 a = new Practice2_7();  //可不要这句
        Practice2_7.increment();
        System.out.print(StaticTest.i);
    }

}
