package com.example.test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class DemoMouse {


    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        doPrintMsg(n);
    }

    public static void doPrintMsg(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            Robot robot = new Robot();
            robot.delay(1000);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.delay(100);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.delay(100);
        }

    }
}
