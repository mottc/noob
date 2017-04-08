package com.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/8/3
 * Time: 15:44
 */
public class TestForMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        int i = map.size();
        System.out.println("map的大小为：" + i);
        String str = map.get("2");
        System.out.println("键2对应的值为：" + str);
    }
}
