package com.leetcode.easy.from1To50;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RomanToInteger13 {

    public static int romanToInt(String roman) {
        Map<String, Integer> config = new HashMap<>();
        config.put("M", 1000);
        config.put("CM", 900);
        config.put("D", 500);
        config.put("CD", 400);
        config.put("C", 100);
        config.put("XC", 90);
        config.put("L", 50);
        config.put("XL", 40);
        config.put("X", 10);
        config.put("IX", 9);
        config.put("V", 5);
        config.put("IV", 4);
        config.put("I", 1);

        int index = roman.length() - 1;
        int result = 0;
        while(index > -1) {
            if(index == 0) {
                result += config.get(roman.substring(0, 1));
                index--;
            } else {
                String combine = roman.substring(index-1, index+1);
                if(config.containsKey(combine)) {
                    result += config.get(combine);
                    index = index -2;
                } else {
                    result += config.get(roman.substring(index, index+1));
                    index--;
                }
            }
        }

        return result;
    }

    /**
     * CM 可以认为是 C + M -200 = 900
     * IV 可以认为是 I + V -2 = 4
     * 所以CM可以当作-200，计算CM=100 + 1000 - 200 = 900
     * @param roman
     * @return
     */
    public static int romanToInt2(String roman) {
        Map<String, Integer> config = new HashMap<>();
        config.put("M", 1000);
        config.put("CM", -200);
        config.put("D", 500);
        config.put("CD", -200);
        config.put("C", 100);
        config.put("XC", -20);
        config.put("L", 50);
        config.put("XL", -20);
        config.put("X", 10);
        config.put("IX", -2);
        config.put("V", 5);
        config.put("IV", -2);
        config.put("I", 1);

        int result = 0;
        for(String key: config.keySet()) {
            result += countStr(roman, key)*config.get(key);
        }

        return result;
    }

    private static int countStr(String str, String subStr) {
        String temp = str.replace(subStr, "");
        int occ = (str.length() - temp.length()) / subStr.length();
        return occ;
    }

    public static void main(String[] args) {
        
        System.out.print(romanToInt2("LVIII"));
    }
}
