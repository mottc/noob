package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/27
 * Time: 10:52
 */
public class VarArgs {
    public void print(String[] args){
        for (String str: args){
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        VarArgs a = new VarArgs();
        String[] b = {"a","c"};
        a.print(b);
    }
}
