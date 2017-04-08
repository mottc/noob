package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/25
 * Time: 16:35
 */
public class DogForInt {
    private DogForInt(){
        System.out.println("barking!");

    }
    private DogForInt(int i){
        System.out.println("howling!");
    }
    public DogForInt(int i,double j){
        System.out.println("int first");
    }
    protected DogForInt(double j,int i){
        System.out.println("int last");
    }
    public static DogForInt creat(){
        return new DogForInt();
    }

    public static void main(String[] args) {
        DogForInt a = new DogForInt();
        DogForInt b = new DogForInt(5);
        DogForInt c = new DogForInt(1,1.6);
        DogForInt d = new DogForInt(6.2,7);
        DogForInt.creat();
    }
}
