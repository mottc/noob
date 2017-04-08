package com.practice;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/8/3
 * Time: 15:35
 */
public class TestForSet {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        /*添加*/
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("d");
        /*遍历*/
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
        }
    }
}
