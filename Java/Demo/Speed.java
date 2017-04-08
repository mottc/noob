package com.practice;

import java.util.Scanner;

public class Speed {
    float distance;
    float time;

    public static void main(String[] args) {
        Speed speed = new Speed();
        Scanner reader = new Scanner(System.in);
        speed.distance = reader.nextFloat();
        speed.time = reader.nextFloat();
        System.out.println(speed.speed(speed.distance, speed.time));
    }

    public float speed(float distance, float time) {
        float speed = distance / time;
        return speed;
    }
}
