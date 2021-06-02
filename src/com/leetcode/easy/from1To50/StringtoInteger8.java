package com.leetcode.easy.from1To50;

/**
 * @program: MyLeetCode
 * @description:
 * @author: startsi
 * @create: 2021-06-02 20:33
 **/

/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * ASCII - = 45
 * ASCII + = 43
 * ASCII . = 46
 */
public class StringtoInteger8 {

    public static void main(String[] args) {

        StringtoInteger8 leet = new StringtoInteger8();
        System.out.println(leet.myAtoi2("s"));
    }
    public int myAtoi2(String s) {
        System.out.println(Double.parseDouble("+123.11"));
        return 0;
    }
    public int myAtoi(String s) {
        boolean positive = true;
        int result = 0;
        if(s == null) {
            return result;
        }
        s = s.trim();
        if("".equals(s)) {
            return result;
        }
        char[] arr = s.toCharArray();
        int i=0;
        if((int)arr[i] == 45) {
            positive = false;
            i++;
        } else if ((int)arr[i] == 43) {
            i++;
        }
        for(; i < arr.length; i++) {
            if((int)arr[i] == 46 ) {

                continue;
            }
            if((int)arr[i] <= 9 ) {

            }
        }
//        System.out.println((int)'.');
        return 0;
    }
}
