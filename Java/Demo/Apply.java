package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/29
 * Time: 14:33
 */
class Pro{
    Object p(Object input){
        return input;
    }
}
public class Apply extends Pro {

    int p(double a){
        return (int)a;
    }

    public static void main(String[] args) {
        Apply a = new Apply();
        System.out.println(a.p("s"));
    }

}
