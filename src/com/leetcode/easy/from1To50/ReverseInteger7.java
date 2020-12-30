package com.leetcode.easy.from1To50;

public class ReverseInteger7 {

    public static int reverse(int x) {
        try {
            if (x == 0) return x;
            String s = String.valueOf(Math.abs(x));
            String str = new StringBuffer(s).reverse().toString();
            int re = Integer.valueOf(str);
            return x<0 ? -re : re;
        } catch (Exception e) {
            return 0;
        }
    }

    public static int reverse2(int x) {
        if (x == 0) return x;
        int result = 0;
        while( x != 0) {
            int p = x % 10;
            x = x / 10;

            result = result * 10 + p;
        }
        return result;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        int s = -120;
        int s = 1534236469;
//        int s1 = 9646324351;
//        int s1 = 1056389759;

        System.out.println(964632435 * 10);

//        System.out.println(reverse2(s));
//        System.out.println(System.currentTimeMillis() - start );
    }
}
