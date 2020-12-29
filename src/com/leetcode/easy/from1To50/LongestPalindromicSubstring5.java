package com.leetcode.easy.from1To50;

public class LongestPalindromicSubstring5 {

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
    public static String longestPalindrome2(String s) {
        String reverseStr = new StringBuffer(s).reverse().toString();
        int n = s.length();
        String result = "";
        int max = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++) {
                String tempStr = s.substring(i, j);
                int index = reverseStr.indexOf(tempStr);
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
                start = i - (len - 1) / 2;
                end = i + len / 2;
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
        return R - L - 1;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        String s = "cbbd";
//        String s = "abacdfgdcaba";
//        String s = "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew";
//                 "abacdgfdcaba"
        String s = "abba";
        System.out.println(longestPalindrome3(s));
        System.out.println(System.currentTimeMillis() - start );
    }
}
