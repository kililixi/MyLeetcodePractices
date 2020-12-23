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

        // O(n^2) indexOf也是一个for循环
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

    public static int lengthOfLongestSubstring2(String s) {
        if(s == null || "".equals(s)) {
            return 0;
        }
        int count = 0, i = 0, j = 0;
        int total = s.length();
        Set<Character> set = new HashSet<>();
//        set.add(s.charAt(i));
        while(i < total && j < total) {
            if(set.contains(s.charAt(j))) {
                set.remove(s.charAt(i++));
            } else {
                set.add(s.charAt(j++));
                count = Math.max(count, j-i);
            }
        }

        return count;
    }

    public int lengthOfLongestSubstring3(String s) {

        Map<Character, Integer> map= new HashMap<>();
        int start=0, len=0;

        // abba
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (map.get(c) >= start)
                    start = map.get(c) + 1;
            }
            len = Math.max(len, i-start+1);
            map.put(c, i);
        }

        //
        // 1 a 0
        // 2 b 1
        // 3
        return len;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        String input = "abcabcbb";
//        String input = "abcdbc";
//        String input = "dvdf";
//        String input = "bbbbbb";
//        String input = "pwwkew";
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
    public static int lengthOfLongestSubstringOfficial1(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j))
                    ans = Math.max(ans, j - i);
        return ans;
    }

    public static int lengthOfLongestSubstringOfficial2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
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
