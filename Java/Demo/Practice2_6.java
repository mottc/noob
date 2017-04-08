package com.practice;

public class Practice2_6 {
    String s = "Hello";
    int storage(String s){

        return s.length()*2;
    }
    void print(){

        System.out.print(storage(s));
    }

    public static void main(String[] args) {
        Practice2_6 a = new Practice2_6();
        a.print();
    }
}
