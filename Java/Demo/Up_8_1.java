package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/28
 * Time: 14:58
 */

class Cycle{

}
class Unicycle extends Cycle{

}
class Bicycle extends Cycle{

}
class Tricycle extends Cycle{

}
public class Up_8_1 {
    public static void ride(Cycle a){
        System.out.println("victory");
    }
    public static void main(String[] args) {
        Unicycle a = new Unicycle();
        Bicycle b = new Bicycle();
        Tricycle c = new Tricycle();
        ride(a);            //向上转型
        ride(b);            //向上转型
        ride(c);            //向上转型
    }
}
