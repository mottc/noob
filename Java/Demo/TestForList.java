package com.practice;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/8/3
 * Time: 11:34
 */
public class TestForList {
    public static void main(String[] args) {
        ArrayList<String> arry = new ArrayList<>(); //新建列表
        //添加
        arry.add("a");
        arry.add("b");
        arry.add("c");
        arry.remove(2);    //移除
        for (int i = 0; i < arry.size(); i++) {
            String str = arry.get(i);    //获取
            System.out.println(str);
        }
    }
}
