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
public class E_10_RegularExpressionMatching {

    Result[][] memo;

    public static void main(String[] args) {
//        String s = "dddfsdfsdf";
//        String p = "d*fs.fs.f";
//        String s = "fffsdfsdf";
//        String p = "f*fsdfsdf";
        String s = "aa";
        String p = "a*";
//        String s = "a*a*a*";
//        String p = "aaaaabc";
        long start = System.currentTimeMillis();
        System.out.println(new E_10_RegularExpressionMatching().isMatch3(s, p));
        System.out.println(System.currentTimeMillis()-start);

    }

    /**
     * DP
     * https://leetcode-cn.com/problems/regular-expression-matching/solution/zheng-ze-biao-da-shi-pi-pei-by-leetcode-solution/
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch4(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    /**
     * 思路：
     * 假设 dp(i,j) 代表 text[i:] 与pattern[j:] 是否匹配
     * 然后循环 text 和 pattern
     * for(text) {
     *     for(pattern) {
     *
     *     }
     * }
     * 双重循环就是把每个可能都遍历到，但是又因为有缓存中间的结果，所以计算量不会很大
     *  <!-- fn(i,j) = match(i, j) + match(i, j-1) -->
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatch3(String text, String pattern) {
        // dp(i,j) 代表 text[i:] 与pattern[j:] 是否匹配
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        // 为什么不是从 text.length() -1 开始？
        for (int i = text.length(); i >= 0; i--){
            // pattern 从最后一位开始
            for (int j = pattern.length() - 1; j >= 0; j--){

                // 判断当前i和j对应的text和pattern是否匹配
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));

                // 在对比到 pattern 为 'x*'时
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    // 判断 text[i:] 与 pattern[j:] 是否匹配
                    /**
                     * 如果
                     * 1、dp[i][j+2]
                     * 表示如果 text[i:] 与 pattern[j+2:] 匹配成功
                     * 忽略pattern前面两个匹配符，这种情况代表匹配了0个字符，比如 s=fsdfsdf p=d*fsdfsdf,即删了你这部分正则也不影响
                     *
                     * 2、first_match && dp[i+1][j]
                     * 表示如果 text[i] 与 pattern[j] 匹配成功，并且text[i+1:] 与 pattern[j:] 也匹配成功
                     * 这种情况代表匹配了>=1个text, 比如s=fffsdfsdf p=f*fsdfsdf,删除第一个匹配的字符，pattern不变，继续匹配。
                     *
                     * 以上两个操作结果有一个true的话，代表符合该正则。
                     */
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    // 两种情况
                    // 1 第一次比对
                    // 2 没有*的时候，就当作noStarMatch模式，一个个字符匹配
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 采用DP（动态规划）
     * 简单说就是缓存旧的数据
     * @param text
     * @param pattern
     * @return
     */
    public boolean isMatch2(String text, String pattern) {
        // 缓存中间数据
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    /**
     * 其实就是前面那个方法的优化，把截取字符串的操作变成了遍历字符串，减少了开销而已，这个memo数组有什么必要呢
     * @param i
     * @param j
     * @param text
     * @param pattern
     * @return
     */
    public boolean dp(int i, int j, String text, String pattern) {
        // 如果之前缓存了该位置的值，直接返回
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        // 如果到了pattern的结尾
        if (j == pattern.length()){
            // 如果也到了text的结尾,说明两者长度一致，匹配结束了，没
            // 比如 p='f' t='f' , 从0开始，到 i和j都为1，说明匹配结束，返回true，如果pattern结束了，text没结束，说明有text没匹配到，返回false。
            ans = i == text.length();
        } else{

            // 在i没到text结尾时，通过判断当前各自的第一个字符是否相等，或者pattern为 '.' 来判断第一个字符是否符合
            boolean first_match = (i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) ||
                            pattern.charAt(j) == '.'));

            // 在对比到 pattern 为 'x*' 时，【类似前面那个递归方法种的  如果pattern还剩2，并且第2个是*（匹配零个或多个前面的那一个元素）】
            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){

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
                ans = (dp(i, j+2, text, pattern) ||
                        first_match && dp(i+1, j, text, pattern));
            } else {
                // 没有*的情况下，pattern和 text同时取下一位比较
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
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


enum Result {
    TRUE, FALSE
}

