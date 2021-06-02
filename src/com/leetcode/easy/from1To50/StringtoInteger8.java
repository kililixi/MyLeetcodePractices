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

    public static void main(String[] args) throws Exception {
        StringtoInteger8 leet = new StringtoInteger8();
        System.out.println(leet.myAtoi2("-91283472332"));
        System.out.println(leet.myAtoi("2147483648"));
    }
    public int myAtoi2(String s) {
        System.out.println(Double.parseDouble("words and 987"));
        int a = Integer.MIN_VALUE;
        int b = 2147483647;
        int c = a-1;
        int d = b+1;
        System.out.println(a);   //-2147483648
        System.out.println("a-1: "+ c);     //2147483647
        System.out.println("b+1 " + d);     //-2147483648
        return 0;
    }
    public int myAtoi(String s) throws Exception{
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
            if( (int)arr[i] >= 48 && (int)arr[i] <= 57 ) {
                // 注意是否超过范围
                if( positive && (result > Integer.MAX_VALUE/10) ){
                    result =  Integer.MAX_VALUE;
                    break;
                }
                if( !positive && ( -result < Integer.MIN_VALUE/10) ){
                    result =  Integer.MIN_VALUE;
                    break;
                }
                result = result * 10 + (arr[i] - 48);
            } else {
                break;
            }
        }
        return positive ? result : -result;
    }
}
