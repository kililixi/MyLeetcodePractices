package com.leetcode.easy.from1To50;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/zigzag-conversion/
 *
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 */
public class E_6_ZigZagConversion {

    public static String convert(String s, int numRows) {
        if(numRows == 1) {
            return s;
        }
        // 根据行数，生成 一个列表，元素是 StringBuilder，存放z格式的数字
        List<StringBuilder> ros = new ArrayList<>(numRows);
        for(int i=0; i<numRows; i++) {
            ros.add(new StringBuilder());
        }
        int length = s.length();
        // 标志，是否转向
        boolean flag = true;
        int row = 0;
        for(int i=0; i<length; i++) {
            ros.get(row).append(s.charAt(i));
//            if( i >= (numRows -1 ) && (i+1)%numRows == 0) {
            // 判断是不是可以被 numRows 整除 【-1 是因为i 从0开始】
            if( i% (numRows - 1) == 0) {
                flag = !flag;
            }

            // 根据是否转向来决定是增加还是减少行数
            row += !flag ? 1 : -1;
        }
        String result = "";
        for (int i=0; i<numRows; i++) {
            result += ros.get(i).toString();
        }
        return result;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

//        String s = "PAYPALISHIRING";
        String s = "A";
        System.out.println(convert(s, 2));
        System.out.println(System.currentTimeMillis() - start );
    }
}
