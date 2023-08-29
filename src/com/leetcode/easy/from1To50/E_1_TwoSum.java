package com.leetcode.easy.from1To50;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * 给一组数字和一个target，返回两个加起来等于target的数字，假定只有一个结果，并且同一个数字不能用两次。
 *
 * 使用哈希表存数字数组，值作为key,数组下标作为value，这样可以通过 target-当前值 来简单获取另一个值的下标。
 * 循环数字数组的时候如果target-当前值（当前数字期望的值）在hash中，则获取当前下标和hash的value作为结果返回；
 * 如果不存在，当前值作为key, 数组下标作为value存入hash中。
 *
 */
public class E_1_TwoSum {
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
