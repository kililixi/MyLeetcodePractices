package com.leetcode.easy.from1To50;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/two-sum/
 * 给一组数字和一个target，返回两个加起来等于target的数字，假定只有一个结果，并且同一个数字不能用两次。
 *
 * 使用哈希表，循环的时候把 target-当前值 作为key, 下标作为 value；如果 下次循环时的 (target-当前值)存在于哈希表中，则根据下标(value)取对应的值
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
