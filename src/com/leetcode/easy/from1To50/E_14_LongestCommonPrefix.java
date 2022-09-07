package com.leetcode.easy.from1To50;

/**
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * 超出最长的共有前缀字符
 */
public class E_14_LongestCommonPrefix {

    /**
     * 只是找前缀，那先找到最短的那个，以最短的为准，再多也不会超过最短的.
     * 从小到大一个个比，如
     * [abc, abcd, abcdef]
     * 从a 到 ab 到 abc
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 1) {
            return strs[0];
        }
        String minStr = strs[0];
        for(int i=1; i<strs.length; i++) {
            if(strs[i].length() < minStr.length()) {
                minStr = strs[i];
            }
        }

        String commonPrefix = "";
        for(int i=1; i<minStr.length()+1; i++) {
            String splitStr = minStr.substring(0, i);
            boolean match = true;
            for(String str: strs) {
                if(!str.startsWith(splitStr)) {
                    match = false;
                    break;
                }
            }

            if(match) {
                commonPrefix = splitStr;
            }
        }

        return commonPrefix;
    }

    /**
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix2(String[] strs) {
        String commonPrefix = strs[0];
        for(int i=0; i<strs.length; i++) {
            // 循环到匹配为止,如果没有，就是""
            while(!strs[i].startsWith(commonPrefix)) {
                commonPrefix = commonPrefix.substring(0, commonPrefix.length()-1);
            }
        }
        return commonPrefix;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"b","a"};
        System.out.println(longestCommonPrefix2(strs));
    }
}
