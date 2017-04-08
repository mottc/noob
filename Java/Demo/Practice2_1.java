package com.practice;
/*
* 验证Java执行了默认初始化
* */

//public class Practice2_1 {
//    int i;
//    char c;
//    public Practice2_1(){
//        System.out.println(i);
//        System.out.println(c);
//    }
//    public static void main(String[] args) {
//        new Practice2_1();
//    }
//}
public class Practice2_1{
    int i;
    char c;
    public static void main(String[] args) {
        Practice2_1 a = new Practice2_1();
        System.out.println(a.i);
        System.out.println(a.c);
    }
}

