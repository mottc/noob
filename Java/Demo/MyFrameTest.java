package com.practice;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: mottc
 * Date: 2016/7/27
 * Time: 9:34
 */
public class MyFrameTest extends Frame {
    private Button quit = new Button("qiut");

    public MyFrameTest(){
        super("Test Window");
        add(quit);
        pack();
        setVisible(true);
    }
    public static void main(String[] args) {
        MyFrameTest myFrame = new MyFrameTest();


    }
}
