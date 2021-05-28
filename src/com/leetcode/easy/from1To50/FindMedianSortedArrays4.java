package com.leetcode.easy.from1To50;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 */
public class FindMedianSortedArrays4 {

    /**
     * 思路，合并两个数组。调用数组的方法排序，然后直接获取中间的数字
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] array = Arrays.copyOf(nums1, nums1.length + nums2.length);//数组扩容
        System.arraycopy(nums2, 0, array, nums1.length, nums2.length);

        array = Arrays.stream(array).sorted().toArray();
        if(array.length %2 == 0) {
            return Double.valueOf(array[array.length/2] + array[array.length/2 -1 ]) /2;
        } else {
            return array[array.length/2];
        }
    }

    /**
     * 思路：
     * 两有组有序的数字数组互相比较 数次，每次比较获取两个较大的数（等于下标一直向右推进）
     * 比较的次数： 假设两数组的长度是 a 和 b ，只要比较  (a + b) / 2 + 1 次，结果就刚好是所需要的数字范围，因为原数组就是有序的，所以获取中位数所需的最大的次数是 a/2 + b/2 + 1
     * 打个比方 [1, 3, 6] 和 [2, 4, 5, 9] 循环次数 (3+4)/2 + 1 = 4
     * 比较次数  数字       结果(before,curr)
     *   1      1 < 2     0,1
     *   2      3 > 2     1,2
     *   3      3 < 4     2,3
     *   4      6 > 4     3,4
     *
     * 最后根据两组数字的长度是奇数还是偶数来判断,中位数是 4
     *
     *
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int median = 0;
        int beformedian = 0;

        int a = 0;
        int b = 0;

        int len = (len1+len2)/2+1;

        for(int i=1; i <= len; i++){
            System.out.println(nums1[a] + "---" + nums2[b]);
            if(a < len1 && b < len2){
                if(nums1[a] > nums2[b]){
                    beformedian = median;
                    median = nums2[b++];
                }
                else{
                    beformedian = median;
                    median = nums1[a++];
                }
            }
            else if( a < len1){
                beformedian = median;
                median = nums1[a++];
            }
            else {
                beformedian = median;
                median = nums2[b++];
            }
        }

        if((len1+len2)%2==1)
            return median;
        else
            return (median+beformedian)*1.0/2;

    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 6};
        int[] nums2 = new int[]{2, 4, 5, 9};

        System.out.println(findMedianSortedArrays2(nums1, nums2));
    }

}
