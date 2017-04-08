package com.mottc;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/24
 * Time: 22:11
 */

public class BinarySearch {
    public int binarySearch(int[] data,int aim){
        int start = 0;
        int end = data.length-1;
        int mid = (start+end)/2;
        while(data[mid] != aim && end > start){
            if(data[mid] > aim){
                end = mid-1;
            }else if(data[mid]<aim){
                start = mid+1;
            }
            mid = (start+end)/2;
        }
        return (data[mid]!=aim)? -1 : mid;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        Scanner in = new Scanner(System.in);
        int[] x = {1, 8, 15, 25 ,34, 45, 46, 55};
        System.out.println("请输入您要查找的数字：");
        int num = in .nextInt();
        int b = bs.binarySearch(x,num);
        if (b == -1){
            System.out.println("没有您要查找的数字！");
        }
        else {
            b += 1;
            System.out.println("您要查找的数字在第"+ b +"位。");
        }

    }
}
