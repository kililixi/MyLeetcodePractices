package com.leetcode.easy.from1To50;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestSubstring3 {
    public static int lengthOfLongestSubstring(String s) {
        int count = 0;
        int max = 0;
        char[] chary = s.toCharArray();
        String str = "";

        for(int i=0; i<chary.length; i++) {
            int index = str.indexOf( chary[i] );
            if( index > -1 ) {
                str = str.substring(index +1) + chary[i];
                count = str.length();
            } else {
                str += chary[i];
                count ++;

                if(max < count) {
                    max = count;
                }
            }

        }

        return max;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String input = "abcabcbb";
//        String input = "dvdf";
        System.out.println(lengthOfLongestSubstring2(input));
        System.out.println(System.currentTimeMillis() - start );
    }

    /**
     * 即使是暴力循环，速度也比写的快
     * official
     * Brute force
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j))
                    ans = Math.max(ans, j - i);
        return ans;
    }

    public static boolean allUnique(String s, int start, int end) {
        System.out.println(s.substring(start, end));
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
}
