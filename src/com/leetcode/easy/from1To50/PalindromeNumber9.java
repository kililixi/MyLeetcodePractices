package com.leetcode.easy.from1To50;

/**
 * @program: MyLeetCode
 * @description:
 * @author: startsi
 * @create: 2021-06-03 19:52
 **/

/**
 * https://leetcode.com/problems/palindrome-number/
 *
 * 给定一个数字，如果是回文返回true 不是返回false
 */
public class PalindromeNumber9 {

    public static void main(String[] args) {
        System.out.println(isPalindrome2(10));
    }
    public static boolean isPalindrome(int x) {
        /**
         * x < 0不行
         * x 能被10整除，也不行，因为 10 那回文是(int)01， 01 = 1 所以也不行【0是回文，例外】
         */
        if(x < 0 || ( x % 10 == 0 && x!= 0) ) {
            return false;
        }

        String s = String.valueOf(x);
        char[] arr = s.toCharArray();
        int i = 0;
        int j = s.length() -1;

        while( i < j && arr[i] == arr[j]) {
            i++;
            j--;
        }

        if(i < j) {
            return false;
        }
        return true;
    }

    public static boolean isPalindrome2(int x) {
        if(x < 0 ) {
            return false;
        }

        int num = 0;

        /**
         * 当输入的值小于reversed的值后，说明已经过半了，
         * 过半后可以通过 1、 x == num 【偶数个的时候判断，相等说明是回文， 比如说 1221 当分别是 x = 12 num = 21】
         * 2、奇数的时候，判断下num/10 是否等于x，因为奇数的时候，中间那个数字对于回文来说没有意义，所以通过 num/10去掉 num的最后一位【也是输入值的中间的数】
         */
        while( x > num) {
            num = num * 10 + x%10;
            x = x/10;
        }

        return x == num || x == num/10;
    }
}
