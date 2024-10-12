package algorithm.myarray;


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


    /** 2.给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。使用 O(1) 额外空间并原地修改输入数组*/

    /**
     * create by: Ting
     * description: TODO 暴力解法，两个for循环
     * create time: 11/03/2024 22:29
     */
    public int[] deleteArrayValue(int [] nums,int value){
        int length = nums.length;
        int flag = 1;
//         循环原数组
        for (int i = 0; i < length; i++) {
            if (nums[i] == value){
                for(int index = i + 1; index < length; index ++){
                    if(nums[index] != value){
                        nums[i] = nums[index];
                        nums[index] = value;
                        break;
                    }
                }
            } else {
                flag ++;
            }
        }
        return nums;
    }


    /**
     * create by: Ting
     * description: TODO 只循环一遍，循环的时候找出不是value的值，找到一个就往前面数组丢，前面数组的位置+1就行
     * create time: 16/03/2024 21:09
     */
    public int[] deleteArrayValue2(int [] nums,int value){
        int length = nums.length, flag = 0;
        for(int i = 0; i < length;i++){
            if(nums[i] != value){
                nums[flag] = nums[i];
                flag ++;
            }
        }
        return nums;
    }

    /** Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.*/

    /**
     * create by: Ting
     * description: TODO
     * create time: 17/03/2024 13:58
     */
    public int[] sortedSquares(int[] nums){
        int length = nums.length,i = 0;
        int j = length -1;
        int [] res = new int[length];
        for(int index = length-1; index > -1;index--){
            if(nums[i] * nums[i] < nums[j] * nums[j]){
                res[index] = nums[j] * nums[j];
                j--;
            }else {
                res[index] = nums[i] * nums[i];
                i++;
            }
        }
        return res;
    }

    /** Given an array of positive integers nums and a positive integer target, return the minimal length of a
     subarray
     whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.*/

    /**
     * create by: Ting
     * description: TODO
     * create time: 17/03/2024 19:46
     */
    public int minSubArrayLen(int target, int[] nums) {
        int res = Integer.MAX_VALUE, length = nums.length;
        int sum = 0,j = 0;
        for(int i = 0; i < length && j < length;i++){
            sum = nums[i] + sum;
            while(sum >= target){
                res = Math.min(res,i-j+1);
                sum = sum - nums[j];
                j++;
            }
        }
        return res == Integer.MAX_VALUE?0:res;
    }

    /** Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
     */

    /**
     * create by: Ting
     * description: TODO
     * create time: 17/03/2024 20:29
     */
    public int[] spiralArray(int n){
        int[] res = {};

        return res;
    }


}

