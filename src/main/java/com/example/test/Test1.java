package com.example.test;

public class Test1 {
    public static void main(String[] args) {
        System.out.println(fib(3));
        System.out.println(fibSum(5));
        System.out.println(sum(100));
    }

    /**
     * 菲波那切数列
     *
     * @param a
     * @return
     */
    private static int fib(int a) {
        if (a < 1) {
            return 0;
        }
        if (a == 1 || a == 2) {
            return 1;
        } else {
            return fib(a - 1) + fib(a - 2);
        }
    }

    private static int fibSum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + fib(i);
        }
        return sum;
    }

    private static int sum(int a) {
        if (a < 1) {
            return 0;
        }
        if (a == 1) {
            return 1;
        } else {
            return a + sum(a - 1);
        }
    }
}
