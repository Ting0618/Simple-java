package Algorithm;

import java.util.Arrays;

/**
 * create by: Ting
 * description: TODO algorithm about arrays
 * create time: 10/03/2024 21:48
 */

public class MyArrays {

    // 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

    /**
     * create by: Ting
     * description: TODO normal way
     * create time: 10/03/2024 21:53
     */
    public int normal(int nums[], int target){
        int result = -1;
        int arrLen = nums.length;
        for(int i = 0; i < arrLen; i++){
            if(target == nums[i]){
                result = i;
                break;
            }
        }
        return result;
    }


    /**
     * create by: Ting
     * description: TODO binarySearch,pay attention to array boundaries
     * create time: 11/03/2024 21:28
     */
    public int binarySearch(int nums[], int target){
        int result = -1;
        int arrLen = nums.length;
        int left = 0, right = arrLen - 1;
        int index;
        while (left <= right){
            index = (left + right) / 2;
            if (target < nums[index]){
                right = index - 1;
            } else if (target > nums[index]) {
                left = index + 1;
            } else {
                return index;
            }
        }
        return result;
    }
}
