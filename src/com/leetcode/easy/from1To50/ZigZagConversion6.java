package com.leetcode.easy.from1To50;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion6 {

    public static String convert(String s, int numRows) {
        if(numRows == 1) {
            return s;
        }
        List<StringBuilder> ros = new ArrayList<>(numRows);
        for(int i=0; i<numRows; i++) {
            ros.add(new StringBuilder());
        }
        int length = s.length();
        boolean flag = true;
        int row = 0;
        for(int i=0; i<length; i++) {
            ros.get(row).append(s.charAt(i));
//            if( i >= (numRows -1 ) && (i+1)%numRows == 0) {
            if( i% (numRows - 1) == 0) {
                flag = !flag;
            }
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
