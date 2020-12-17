package com.leetcode.easy.from1To50;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 */
public class TwoSum1 {
    public static int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> result = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            int another = target - nums[i];
            if(result.containsKey(another)) {
                return new int[]{result.get(another), i};
            }
            result.put(nums[i], i);
        }

        throw new IllegalArgumentException("不存在两个数");
    }

    public static void main(String[] args) throws Exception {
        int[] input = new int[]{2,7,11,15};
        int target = 17;
        Arrays.stream(twoSum(input, target)).forEach(v -> System.out.println(v));
    }
}
