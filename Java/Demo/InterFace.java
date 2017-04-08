package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/25
 * Time: 14:50
 */
interface Speaker{
    public void speak();
    public void announce(String str);
}
public class InterFace implements Speaker{
    private String philosophy;
    public InterFace(String philosophy){
        this.philosophy = philosophy;
    }
    public void speak(){
        System.out.println(philosophy);
    }
    public void announce(String announcement){
        System.out.println(announcement);
    }
    public void pontificate(){
        for (int count = 1; count <= 5; count++)
            System.out.println(philosophy);
    }
}
class Dog implements Speaker{
    public void speak(){
        System.out.println("woof");
    }
    public void announce(String announcement){
        System.out.println("woof:" + announcement);
    }
}
class Talking{
    public static void main(String[] args) {
        InterFace a = new InterFace("ssss");
        a.speak();
        a.announce("annnn");
        a.pontificate();
        Dog b = new Dog();
        b.speak();
        b.announce("wo`");
//        Speaker current;
//        current = new Dog();
//        current.speak();
//        current = new InterFace("I think");
//        current.speak();
//        ((InterFace)current).pontificate();

    }
}
