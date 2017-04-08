package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/29
 * Time: 13:59
 */
interface face1{
    public void print();
}
interface face2{
    public void print();
}
public class Test55 implements face1,face2{
    public void print(){
        System.out.println("ccc");
    }

    public static void main(String[] args) {
        Test55 a = new Test55();
        a.print();
    }
}
