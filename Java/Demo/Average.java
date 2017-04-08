package com.practice;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/26
 * Time: 9:41
 */
public class Average {
    static float ave = 0;
    static int sumScore = 0;
    static int sumPeople = 0;
    int score;
    Average(int score){
        this.score = score;
        sumScore += score;
        sumPeople += 1;
    }
    static float getAve(){
        ave = (float)sumScore/sumPeople;
        return ave;
    }

    public static void main(String[] args) {
        Average a = new Average(80);
        Average b = new Average(90);
        Average c = new Average(56);
        Average d = new Average(86);

        System.out.println(Average.getAve());



    }
}
