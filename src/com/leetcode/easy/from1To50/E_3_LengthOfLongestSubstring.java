package com.leetcode.easy.from1To50;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * 给定一个字符串，找出最长的不重复的段落
 */
public class E_3_LengthOfLongestSubstring {

    /**
     * 时间复杂度： O(n^2)
     * 将字符串分解成字符数组
     * 定义一个字符串作为当前最长的不重复字符串 str ，当前不重复次数 count, 最大的不重复个数 max; 开始循环数组
     * 循环体中，通过 indexOf 判断当前字符在不在str中。
     * 如果不在，则把count+1,并把字符加到str末尾，并把 max 赋值 Math.max(max, count)；
     * 如果存在，则 str从下标为当前char开始截取，再加上当前char: str.substring(index +1) + chary[i] ，count = len(str)
     * // 比如 str = abc, 当前循环的char是b ,那新的str应该是 cb
     * 知道循环结束
     * @param s
     * @return
     */
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

    /**
     * 假设没有重复的字段在str的开始和结束的下标分别为 i 和 j, 初始为0；当前最大长度count 为 0；
     * XXXXXXXX
     * ij
     *
     * 再用set来保证，字符只有一个
     *
     * while 循环，终止条件为下标i或j达到str的长度为止
     * 循环体中，先移动右边的下标(j), 判断字符是否在set中，如果不存在，则将右边的下标对应的字符加入set中，并向右移动1位（j++）,计算 Math.max(count, j-i),赋值给count
     * 如果存在，则从set中移除左边下标对应的值。 左边的下标向右移动(i++)。
     *
     * 相当于向右( i 到 j 的区间内)平移
     *
     * @param s
     * @return
     */
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
                count = Math.max(count, j-i); // 上面一步已经对 j ++ 了
            }
        }

        return count;
    }

    /**
     * 思路：
     * 记录字符出现的下标位置，当下次发现相同的字符时，直接从上一个相同字符的位置开始计算
     *
     * 定义 当前开始位置 start, 以及不重复长度len ，都为0
     * 定义HashMap , 保存下标出现的位置
     *
     * 对字符数组进行循环，
     * 循环体中：
     * 如果map中存在对应的字符，说明有重复，判断是否 >=start(再从小于start的下标开始计算，没有意义)
     *
     * 如果大于等于，则从 （该下标 + 1） 作为新的开始位置，比如 abcbacdb , 当进行到第二个b(3)时，应该从 第一个b的下标 (1) + 1 的 c开始
     *
     * 当前长度为 i - start + 1 ，比如上面的例子 时 cb
     * 进行 len = Math.max(len, i-start + 1 )的操作
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {

        Map<Character, Integer> map= new HashMap<>();
        int start=0, len=0;

        // abba
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                //
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
        String input = "abcbacdb";
//        String input = "abba";
//        String input = "abcdbc";
//        String input = "dvdf";
//        String input = "bbbbbb";
//        String input = "pwwkew";
        System.out.println(lengthOfLongestSubstring3(input));
        System.out.println(System.currentTimeMillis() - start );
    }

    /**
     * 即使是暴力循环，速度也比写的快
     *
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
                if (allUnique(s, i, j))  // 判断s从i 到 j 是否有重复，都没有重复就计算长度
                    ans = Math.max(ans, j - i);
        return ans;
    }

    /**
     * 类似 lengthOfLongestSubstring2
     * @param s
     * @return
     */
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

    /**
     * 判断给定的字符串s ,从 start 到 end ，是否没有重复的
     * @param s
     * @param start
     * @param end
     * @return
     */
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
