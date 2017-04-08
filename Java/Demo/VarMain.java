package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/27
 * Time: 10:57
 */
public class VarMain {
    public void print(String...args){
        for (String str: args) {
            System.out.println(str);
        }
    }
    public static void main(String...args) {
        VarMain a = new VarMain();
        VarMain b = new VarMain();
        a.print("a", "b");
    }
}
/*public class VarMain{
    public static void print(String...args){
        for (String str: args) {
            System.out.println(str);
        }
    }
    public static void main(String...args) {
        print("a","b","c");
    }
}*/
