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

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        String s = "cbbd";
        String s = "abacdfgdcaba";
//        String s = "euazbipzncptldueeuechubrcourfpftcebikrxhybkymimgvldiwqvkszfycvqyvtiwfckexmowcxztkfyzqovbtmzpxojfofbvwnncajvrvdbvjhcrameamcfmcoxryjukhpljwszknhiypvyskmsujkuggpztltpgoczafmfelahqwjbhxtjmebnymdyxoeodqmvkxittxjnlltmoobsgzdfhismogqfpfhvqnxeuosjqqalvwhsidgiavcatjjgeztrjuoixxxoznklcxolgpuktirmduxdywwlbikaqkqajzbsjvdgjcnbtfksqhquiwnwflkldgdrqrnwmshdpykicozfowmumzeuznolmgjlltypyufpzjpuvucmesnnrwppheizkapovoloneaxpfinaontwtdqsdvzmqlgkdxlbeguackbdkftzbnynmcejtwudocemcfnuzbttcoew";
//        [9, 11]
//
//        [12 - 11 - 1, 12 - 9 -1]
//        [0, 2]
//                 "abacdgfdcaba"
//        String s = "babad";
        System.out.println(longestPalindrome2(s));
        System.out.println(System.currentTimeMillis() - start );
    }
}
