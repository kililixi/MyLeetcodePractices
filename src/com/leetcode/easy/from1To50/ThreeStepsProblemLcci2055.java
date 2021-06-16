package com.leetcode.easy.from1To50;

/**
 * 题目： 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，
 * 你需要对结果模1000000007。n范围在[1, 1000000]之间。
 * 解题思路： 这个题目可以采用动态规划的方法来解题，首先从最上层去思考问题，当我跳到n层时，有sum_n种跳法，那么我再增加一层到n+1，
 * 在跳到n+1层，是不是有三种跳法呢，即在n层跳1步，n-1层跳2步，n-2层跳3步，由此推出状态转移方程dp[i]=dp[i-1] + dp[i-2] + dp[i-3];因为int型数据会溢出，所以只要取模来解决溢出问题就行。
 * https://leetcode-cn.com/problems/three-steps-problem-lcci/comments/907592
 */
public class ThreeStepsProblemLcci2055 {

    public static void main(String[] args) {
        System.out.println(waysToStep(5));
    }

    public static int waysToStep(int n) {
        if(n == 2) return n;
        if(n==3) return 4;
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 4;

        for(int i=4; i<=n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
            dp[i] = (dp[i] + dp[i-3]) % 1000000007;
        }
        return dp[n];
    }
}
