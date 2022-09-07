package com.leetcode.easy.from1To50;

/**
 * https://leetcode.com/problems/container-with-most-water/
 * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
 *
 * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class E_11_ContainerWithMostWater {

    /**
     * 暴力循环
     * 遍历每两条线，找出最大容量
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int maxCapicity = 0;
        for(int i=0; i<height.length -1; i++) {
            for(int j=i+1; j<height.length; j++) {
                maxCapicity = Math.max(maxCapicity, Math.min(height[i], height[j]) * (j - i));
            }
        }
        return maxCapicity;
    }

    /**
     * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
     * 可以从开始和结尾的两条线开始，向中新靠拢，保存最大的capicity值。然后下一次循环从较短的线进一步。
     * @param height
     * @return
     */
    public static int maxArea2(int[] height) {
        int maxCapicity = 0;
        int l = 0;
        int r = height.length-1;
        while(l < r) {
            maxCapicity = Math.max(maxCapicity, Math.min(height[l], height[r]) * (r - l));
            if(height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
        }
        return maxCapicity;
    }

    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
//        int[] height = new int[]{1,1};
        System.out.println(maxArea2(height));
    }
}
