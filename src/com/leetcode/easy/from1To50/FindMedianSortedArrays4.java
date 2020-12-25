package com.leetcode.easy.from1To50;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FindMedianSortedArrays4 {

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

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 6};
        int[] nums2 = new int[]{2, 4, 5, 9};

        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

}
