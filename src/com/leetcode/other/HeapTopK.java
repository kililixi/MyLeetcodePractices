package com.leetcode.other;

public class HeapTopK {

    public static void main() throws Exception {
        int[] arr = {4,5,1,6,2,7,3,8};
        // 8,7,6,5,4,3,2,1
        // 求第5个大的元素
        int k = 5;

        int[] minHeap = new int[k+1];
        // 将前K个元素放到新数组中，该数组将作为堆
        System.arraycopy(arr, 0, minHeap, 1, k);
        // 建堆
        buildHeap(minHeap, k);

        for(int i = k; i< arr.length; i++) {
            // 接着处理剩下的元素，只将其与顶堆的元素比较，因为顶堆的元素是堆中最小的
            if(minHeap[1] < arr[i]) {
                minHeap[1] = arr[i];
                // 有变更就堆化一次（就能在顶部3个元素中选出最小的，至于跟其他节点相比是不是更大不重要，因为结果是最K大的元素）
                heapify(minHeap, k, 1);
            }
        }

        int result = minHeap[1];
        System.out.println(result);
    }

    private static void buildHeap(int[] arr, int k) {
        // k/2是最后一个非叶子节点，没必要堆化叶子节点，因为其没有子节点了。
        for(int i=k/2; i>0; i--) {
            heapify(arr, k, i);
        }
    }

    private static void heapify(int[] arr, int k, int i) {
        // 假设最小的节点是当前节点，接着跟当前节点的两个子节点比较，把最小的挑出来
        // 对于小顶堆，非叶子节点i的左子节点，下标为2*i; 右子节点，下标为2*i+1
        int minPos = i; // 先假设当前节点为最小值
        while(true) {
            if(2*i <= k && arr[i] > arr[2*i]) { // 如果大于左节点
                minPos = 2*i; // 则最小值为左节点
            }
            if( 2*i+1 <= k && arr[minPos] > arr[2*i+1] ) { // 如果大于右节点
                minPos = 2*i+1;
            }
            if(minPos == i) { // 如果最小值等于当前值，证明已经是正常的小顶堆节点
                break;
            } else {
                swap(arr, minPos, i); // 说明需要调整，交换
                i = minPos; // 将最小值设置为当前节点i
            }

        }
    }

    private static void swap(int[] arr, int minPos, int i) {
        int temp = arr[i];
        arr[i] = arr[minPos];
        arr[minPos] = temp;
    }
}
