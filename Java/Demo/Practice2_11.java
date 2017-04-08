package com.practice;

public class Practice2_11 {
    int anIntegerRepresentingColors;
    void changeTheHueOfTheColor(int newHue){

        anIntegerRepresentingColors = newHue;
    }

    public static void main(String[] args) {
        Practice2_11 a = new Practice2_11();
        a.changeTheHueOfTheColor(400);
        System.out.println(a.anIntegerRepresentingColors);
    }
}
