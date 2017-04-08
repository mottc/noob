package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/8/2
 * Time: 9:59
 */
public class Outer {
    private String str;

    public Outer(String str) {
        this.str = str;
    }

    class Inner {
        Inner() {
            System.out.println("内部类");
        }

        public void print() {
            System.out.println(str);
        }
    }

    public Inner ship() {
        return new Inner();
    }

    public static void main(String[] args) {
        Outer a = new Outer("HelloWorld");
        Outer.Inner b = a.ship();
        b.print();
        Outer.Inner c = a.new Inner();
    }
}
