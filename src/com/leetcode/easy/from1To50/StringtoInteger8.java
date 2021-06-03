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
//        System.out.println(leet.test());
        System.out.println(leet.myAtoi2("21474836491"));
//        System.out.println(leet.myAtoi2("2147483648"));
    }

    /**
     * 还是要判断是否超过范围
     * @param s
     * @return
     */
    public int myAtoi2(String s) {
        int result = 0;
        boolean positive = true;
        // 过滤 s 的 非法字符
        if(s == null) {
            return result;
        }
        s = s.trim();
        if("".equals(s)) {
            return result;
        }
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();

        int i=0;
        if((int)arr[i] == 45) {
            positive = false;
            i++;
        } else if ((int)arr[i] == 43) {
            i++;
        }

        for( ; i < arr.length; i++) {
            if( (int)arr[i] >= 48 && (int)arr[i] <= 57 || (int)arr[i] == 45 || (int)arr[i] == 43 ) {
                sb.append(arr[i]);
            }
        }
        try{
            result = Integer.parseInt(sb.toString());
        }catch (NumberFormatException e) {
            result = positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        return result;
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
                /**
                 * x > 0
                 * 假设 x * 10 + y > Integer.MAX_VALUE 判断x的大小就是
                 *  x > (Integer.MAX_VALUE - y) / 10
                 */
                if( positive && (result > (Integer.MAX_VALUE - (arr[i] - 48) )/10) ){
                    result =  Integer.MAX_VALUE;
                    break;
                }

                /**
                 * x < 0
                 * 假设 x * 10 - y < Integer.MIN_VALUE 判断x的大小就是
                 * x < (Integer.MIN_VALUE + y) / 10
                 */
                if( !positive && ( -result < ( Integer.MIN_VALUE + (arr[i] - 48) )/10) ){
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

    public int test() {
        System.out.println(Integer.parseInt("2147483649"));
        int a = Integer.MIN_VALUE;
        int b = 2147483647;
        int c = a-1;
        int d = b+1;
        System.out.println(a);   //-2147483648
        System.out.println("a-1: "+ c);     //2147483647
        System.out.println("b+1 " + d);     //-2147483648
        return 0;
    }
}
