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

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        int median = 0;
        int beformedian = 0;

        int a = 0;
        int b = 0;

        int len = (len1+len2)/2+1;

        for(int i=1; i <= len; i++){
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
