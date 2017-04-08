package com.practice;

public class Dog1 {
    String name;
    String says;

    public static void main(String[] args) {
        Dog1 dog1 = new Dog1();
        Dog1 dog2 = new Dog1();
        dog1.name = "spot";
        dog1.says = "Ruff!";
        dog2.name = "scruffy";
        dog2.says = "Wurf!";
        System.out.println(dog1.name+" says "+dog1.says);
        System.out.println(dog2.name +" says "+dog2.says);
    }
}
