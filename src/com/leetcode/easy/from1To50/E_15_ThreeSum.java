package com.leetcode.easy.from1To50;

import java.util.*;

public class E_15_ThreeSum {

    /**
     * 暴力
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length<3) {
            return result;
        }
        for(int i=0; i<nums.length-2; i++) {

            for(int j=i+1; j<nums.length-1; j++) {

                for(int k=j+1; k<nums.length; k++) {

                    if(nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triplet = new ArrayList<>();
                        triplet.add(nums[i]);
                        triplet.add(nums[j]);
                        triplet.add(nums[k]);
                        /**
                         * 计算abs的值不行，存在 [[-2,0,2],[-2,1,1]]
                         */
//                        int minus = nums[i] - nums[j] - nums[k];
//                        triplet.so
                        triplet.sort(Comparator.naturalOrder());
                        if(result.contains(triplet)) {
                            continue;
                        }
                        result.add(triplet);


                    }
                }
            }
        }

        return result;
    }

    /**
     * 把原本的3个嵌套循环变成两个。
     * 去重则通过跳过重复的循环来完成
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {

        // 先排序
        Arrays.sort(nums);
        Arrays.stream(nums).forEach(v -> System.out.print(v+","));
        System.out.println("");
        List<List<Integer>> result = new ArrayList<>();
        // 外层循环就是从左到右
        for(int i=0; i<nums.length-2; i++) {
            // 数组排序完后，两边（左侧和右侧重复的值都是没有意义的）
            // 从左边算起，如果当前的值跟前面的值一样，直接跳过当前循环/
            // 如 1,1,2,3
            // 第一次循环 1,1,3 | 1,1,2, | 1,2,3|
            // 第二次循环 1,2,3 重复了，所以跳过
            if( i>0 && (nums[i] == nums[i-1])) {
                continue;
            }
            // 内层循环就是剩余的数组 一个从左边开始算(假设为j)，一个从右边开始算（k）
            // 因为已经排过序了，这样通过结果大于或小于0，可以得出下次循环是j+1(小于0，要增大)或是k-1(大于0)
            // 这样就避免了无效的组合(结果不为0的).
            // 如果结果为0，则右边向左移动一位
            for(int j=i+1, k=nums.length-1; j<k; ) {
                int s = nums[i] + nums[j] + nums[k];
                if(s == 0) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[j]);
                    triplet.add(nums[k]);

                    result.add(triplet);
                    // TODO 为什么是右边的移动一位，而不是中间的向右移动？如果是中间的向右移动要怎么处理
                    // 排完序后，当前ijk的值为0，当前大循环下，单独k往左(减小)或j往右(增大)，都是没有意义的，还有和为0的可能性只有
                    // 两者同时往中间，所以这时是右边的移动一位还是中间的向右移动都可以，但是右边的移动可以同时去除重复的值
                    k--;
                    // 排除重复的（只有符合条件的排除才有意义）,如果右边的往左移了一位之后，还是重复，就跳过
                    // 通过while来跳过，因为可能同时有大于2个的重复数字
                    while( j < k && (nums[k] == nums[k+1])) {
                        k--;
                    }
                } else if(s > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        int[] nums = new int[]{-1,0,1,2,-1,-4};
        int[] nums2 = new int[]{-2,0,1,1,2};
        int[] nums3 = new int[]{0,0,0};
        List<List<Integer>> result = threeSum2(nums3);
        for(List<Integer> array: result) {
            Iterator<Integer> ite = array.iterator();
            while(ite.hasNext()) {
                System.out.print(ite.next() + ",");
            }
            System.out.println("");
        }
    }

}
