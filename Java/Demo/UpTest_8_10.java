package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/28
 * Time: 17:20
 */
class Rodent{
    protected void speak(){
        System.out.println("父类说");
        did();
    }
    public void did(){
        System.out.println("父类做");
    }
}
class Mouse extends Rodent{
    @Override
    public void did(){
        System.out.println("子类做");
    }
}
public class UpTest_8_10 {
    public static void main(String[] args) {
        Rodent a = new Mouse();
        a.did();
        a.speak();
    }
}
