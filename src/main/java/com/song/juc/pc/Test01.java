package com.song.juc.pc;

import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        //System.out.println(sum(a,b));
    }

    public int check(String str) {
        int flag;
        if (str == null && str.length() == 0) {
            flag = 0;
        }
        for (int i = 0; i < str.length(); i++) {
            str.substring(i);
        }

        flag = -1;
        return flag;
    }
}
