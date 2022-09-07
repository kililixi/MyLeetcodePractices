package com.leetcode.easy.from1To50;

/**
 * https://leetcode.com/problems/reverse-integer/
 *
 * 数字反转
 *
 * Input: x = 123
 * Output: 321
 *
 * Input: x = -123
 * Output: -321
 *
 * Input: x = 120
 * Output: 21
 */
public class E_7_ReverseInteger {

    /**
     * 将数字转成字符串，借助 StringBuilder的reverse 方法转向，再判断转化前的数字是否是负数
     * @param x
     * @return
     */
    public static int reverse(int x) {
        try {
            if (x == 0) return x;
            String s = String.valueOf(Math.abs(x));
            String str = new StringBuilder(s).reverse().toString();
            int re = Integer.valueOf(str);
            return x<0 ? -re : re;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     *
     * 123
     * result = 0
     * 取末尾的数字 123 % 10
     *
     * 然后是 result * 10 + 余数
     *
     * 重复
     * @param x
     * @return
     */
    public static int reverse2(int x) {
        if (x == 0) return x;
        int result = 0;
        while( x != 0) {
            int p = x % 10;
            x = x / 10;

            /**
             *
             * 溢出条件有两个，一个是大于整数最大值MAX_VALUE，另一个是小于整数最小值MIN_VALUE，设当前计算结果为ans，下一位为pop。
             * 从ans * 10 + pop > MAX_VALUE这个溢出条件来看
             * 当出现 ans > MAX_VALUE / 10 且 还有pop需要添加 时，则一定溢出
             * 当出现 ans == MAX_VALUE / 10 且 pop > 7 时，则一定溢出，7是2^31 - 1的个位数
             * 从ans * 10 + pop < MIN_VALUE这个溢出条件来看
             * 当出现 ans < MIN_VALUE / 10 且 还有pop需要添加 时，则一定溢出
             * 当出现 ans == MIN_VALUE / 10 且 pop < -8 时，则一定溢出，8是-2^31的个位数
             *
             *
             */
            if( result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && p > 7 ) ) {
                return 0;
            }
            if( result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE/10 && p < -8 ) ) {
                return 0;
            }
            result = result * 10 + p;
        }
        return result;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        int s = -120;
        int s = 1534236469;
//        int s1 = 9646324351;
//        int s1 = 1056389759;

        System.out.println(reverse2(s));
//        System.out.println(System.currentTimeMillis() - start );
    }
}
