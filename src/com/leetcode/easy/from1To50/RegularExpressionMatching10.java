package com.leetcode.easy.from1To50;

/**
 * @program: MyLeetCode
 * @description:
 * @author: startsi
 * @create: 2021-06-03 20:33
 **/

/**
 * https://leetcode.com/problems/regular-expression-matching/
 *
 * 给定一个字符串s 和 模版 p ,判断s是否符合模版
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 */
public class RegularExpressionMatching10 {

    public static void main(String[] args) {
//        String s = "dddfsdfsdf";
//        String p = "d*fs.fs.f";
        String s = "fffsdfsdf";
        String p = "f*fsdfsdf";

        System.out.println(isMatch(s, p));
    }

    public static boolean isMatch(String text, String pattern) {
        // 如果传入的模版为空字符串时，则当s也为空字符串时，代表符合，否则不符合。
        if(pattern.isEmpty()) {
            return text.isEmpty();
        }

        // 如果text不为空，再判断当前各自的第一个字符是否相等，或者pattern为.
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        // 如果pattern还剩2，并且第2个是*（匹配零个或多个前面的那一个元素）
        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            /**
             * 如果
             * 1、忽略pattern前面两个匹配符(pattern.substring(2))
             * 这种情况代表匹配了0个字符，比如 s=fsdfsdf p=d*fsdfsdf,即删了你这部分正则也不影响
             *
             * 2、忽略已经符合匹配的text（通过first_match判断）
             * 这种情况代表匹配了>=1个text, 比如s=fffsdfsdf p=f*fsdfsdf,删除第一个匹配的字符，pattern不变，继续匹配。
             *
             * 以上两个操作结果有一个true的话，代表符合该正则。
             */
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            // 如果没有*，就当作noStarMatch模式，一个个字符匹配
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    /**
     * 没有 * 时的判断，就是通过递归判断每个字符，和 pattern对应位置的是否相等
     */
    public static boolean noStarMatch(String text, String pattern) {
        // 如果传入的模版为空字符串时，则当s也为空字符串时，代表符合，否则不符合。
        if(pattern.isEmpty()) {
            return text.isEmpty();
        }
        // 如果text不为空，再判断当前各自的第一个字符是否相等，或者pattern为.
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        // 递归，判断后的就不传递了
        // substring每次都会产生新的string对象，浪费内存
        return first_match && noStarMatch(text.substring(1), pattern.substring(1));
    }
}
