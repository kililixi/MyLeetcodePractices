package com.leetcode.easy.from1To50;

/**
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * 给定一个字符串，返回最长的回文
 */
public class E_5_LongestPalindromicSubstring {

    private static int count;

    // O(n^3) isPalind 也差不多是n
    public static String longestPalindrome(String s) {
        int n = s.length();
        int ans = 0;
        int a = 0;
        int b = 0;
        String result = "";
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                // 如果是小于当前最大回文长度的，就不要管了
                if ( (j - i ) > ans && isPalind(s, i, j))
//                if ( isPalind(s, i, j))
                    if(ans < (j - i)) {
                        ans = j-i;
                        result = s.substring(i , j);
                    }
        System.out.println(count);
        return result;
    }

    /**
     * 是否是回文
     * @param s
     * @param start
     * @param end
     * @return
     */
    public static boolean isPalind(String s, int start, int end) {
        count ++;
        String tempStr = s.substring(start, end);
        int i = 0;
        int j = tempStr.length() - 1;
        while(i < j ) {
            if(tempStr.charAt(i) != tempStr.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    /**
     * 思路：
     * 假设 t 是 字符串s的一个回文子字符串，下标是[start, end].
     * 假设 s' 是字符串的反转字符串
     *
     * 那么 t在 s 的下标与 t在 s'的下标会有一定关系.
     *
     *
     * Let's say, t is a substring in string s which lies at indices : [start, end].
     * Let's say, s' is reversed string of s.
     * then t' which is reverse of t lies in s' at indices : [L-end-1, L-start-1]
     *
     * e.g. s = "ababba", s' = "abbaba" (s' and s are reverse of each other)
     * L = 6
     * t = "aba" lies at [0, 2] in s i.e. start = 0, end = 2
     * t' = "aba" lies at [3, 5] in s'
     *
     * which should be at [L-end-1, L-start-1] if we are comparing the same substring forward and backward.
     *
     * @param s
     * @return
     */
    // 因为indexOf的原因，复杂度也是 O(n^3)
    public static String longestPalindrome2(String s) {
        String reverseStr = new StringBuffer(s).reverse().toString();
        int n = s.length();
        String result = "";
        int max = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++) {
                String tempStr = s.substring(i, j);
                int index = reverseStr.indexOf(tempStr); // indexof 复杂度也是 O(n)
                // 为什么 这边-1, 因为是下标，当 abacdfgdcaba 到后面的 'aba' 时，下标应该是[9, 11], 但是j是12，因为substr是[)的关系。
                if( index == (n - (j-1) - 1)) {
                    if(tempStr.length() > max) {
                        max = tempStr.length();
                        result = tempStr;
                    }
                }
            }
        return result;
    }

    /**
     * 以一个或两个字符串为中心，判断中心左右两边同等位置的字符串是否一致，然后不断向外
     * 如 'abba'
     *
     * 1、 i=0 先判断a是不是，是，长度为1。 记录为设为1, 开始结尾为0,0
     *    判断ab是不是，不是，长度为0  小于1
     * 2、 i=1 判断b是不是，是；继续，左右字符为ab，不是回文。长度为1。 还是<=1 ，忽略。
     *     判断bb 是不是，是，继续，左右字符为aa，是回文。长度为4, 记录为4，下标为0,3
     * 3、 i=2 判断b是不是，是；继续，左右字符为ba，不是回文。长度为1。 还是<=1 ，忽略
     *      判断ba, 是不是，不是。 略过
     * 4、 i=3 判断a是不是，是，长度为1。 忽略
     *
     * 最后取下标为[0,3] 截取 [0,4)
     * @param s
     * @return
     */
    public static String longestPalindrome3(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;  // 以i 为中心，长度为 len , 所以 对于开始下标来说，是 i- (len -1 ) /2 (为什么-1)
                end = i + len / 2;  // 以i 为中心，长度为 len , 所以 对于结束下标来说，是 i + len /2
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1; // -1 是因为在跳出循环之前，已经对 L 和 R 进行操作了
    }

    public static String longestPalindrome4(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        String max = "";
        for (int i = 0; i < s.length(); i++) {
            String len1 = expandAroundCenter2(s, i, i);
            String len2 = expandAroundCenter2(s, i, i + 1);

            if(len1.length() < len2.length()) {
                len1 = len2;
            }

            if(len1.length() > max.length()) {
                max = len1;
            }
        }
        return max;
    }

    private static String expandAroundCenter2(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return s.substring(L + 1, R);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        String s = "cbbd";
//        String s = "abacdfgdcaba";
//        String s = "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew";
//                 "abacdgfdcaba"
        String s = "abba";
//        System.out.println(isPalind(s, 0, s.length()));
        System.out.println(longestPalindrome4(s));
        System.out.println(System.currentTimeMillis() - start );
    }

}
