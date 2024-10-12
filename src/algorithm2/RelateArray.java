package algorithm2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

/**
 * @author ting
 */
public class RelateArray {

    /**
     * create by: Ting
     * description: TODO 二分查找
     * create time: Jul 09 2024 22:56
     */
    public int rearch(int[] nums, int target) {
        int result = -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return result;
    }

    /**
     * create by: Ting
     * description: TODO 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * create time: Jul 09 2024 23:07
     * solution：是把要保留的值丢到前面去（保留），所以只要找要留下的值并对其进行操作就好，不保留的值直接覆盖
     */
    public int removeElement(int[] nums, int val) {
        int flag = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[flag] = nums[i];
                flag++;
            }
        }
        return flag;
    }

    /**
     * create by: Ting
     * description: TODO 给你一个 非严格递增排列 的数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。元素的 相对顺序 应该保持 一致 。然后返回 nums 中唯一元素的个数。
     * create time: Jul 14 2024 22:40
     */
    public int removeDuplicates(int[] nums) {
        int flag = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                flag++;
            }
            if (i > 0 && nums[i] > nums[flag - 1]) {
                nums[flag] = nums[i];
                flag++;
            }
        }
        return flag;
    }

    /**
     * create by: Ting
     * description: TODO 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * create time: Jul 14 2024 23:26
     */
    public void moveZeroes(int[] nums) {
        int flag = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[flag];
                nums[flag] = nums[i];
                nums[i] = temp;
                flag++;
            }
        }
    }

    /**
     * create by: Ting
     * description: TODO 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * create time: Jul 14 2024 23:52
     */
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int left = 0, right = nums.length - 1, index = nums.length - 1;
        while (left <= right) {
            int leftSqure = nums[left] * nums[left];
            int rightSqure = nums[right] * nums[right];
            if (leftSqure <= rightSqure) {
                res[index] = rightSqure;
                right--;
            } else {
                res[index] = leftSqure;
                left++;
            }
            index--;
        }
        return res;
    }

    /**
     * create by: Ting
     * description: TODO 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
     * create time: Jul 15 2024 03:40
     */
    public boolean backspaceCompare(String s, String t) {
        char[] sarr = s.toCharArray();
        char[] tarr = t.toCharArray();
        int flagS = 0, flagT = 0;
        // 处理s字符串，取得回退之后得结果
        for (int i = 0; i < sarr.length; i++) {
            if (sarr[i] != '#') {
                sarr[flagS] = sarr[i];
                flagS++;
            } else if (flagS > 0) {
                flagS--;
            }
        }
        // 处理t字符串，取得回退之后得结果
        for (int i = 0; i < tarr.length; i++) {
            if (tarr[i] != '#') {
                tarr[flagT] = tarr[i];
                flagT++;
            } else if (flagT > 0) {
                flagT--;
            }
        }
        // 比较两个数组
        if (flagS != flagT) return false;
        else {
            for (int i = 0; i < flagS; i++) {
                if (sarr[i] != tarr[i]) return false;
            }
        }
        return true;
    }

    /**
     * create by: Ting
     * description: TODO Given an array of positive integers nums and a positive integer target, return the minimal length of a
     * subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
     * create time: Jul 16 2024 01:14
     * note: 注意为什么设置min = Integer.MAX_VALUE，以及最后的返回值。
     */
    public int minSubArrayLen(int target, int[] nums) {
        int flag = 0, sum = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            while (sum >= target) {
                min = Math.min(min, i - flag + 1);
                sum = sum - nums[flag];
                flag++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * create by: Ting
     * description: TODO 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     * create time: Jul 18 2024 00:29
     */
    public int[][] generateMatrix(int n){
        int[][] nums = new int[n][n];
        int startX = 0, startY = 0;  // 每一圈的起始点
        int offset = 1;
        int count = 1;  // 矩阵中需要填写的数字
        int loop = 1; // 记录当前的圈数
        int i, j; // j 代表列, i 代表行;

        while (loop <= n / 2) {

            // 顶部
            // 左闭右开，所以判断循环结束时， j 不能等于 n - offset
            for (j = startY; j < n - offset; j++) {
                nums[startX][j] = count++;
            }

            // 右列
            // 左闭右开，所以判断循环结束时， i 不能等于 n - offset
            for (i = startX; i < n - offset; i++) {
                nums[i][j] = count++;
            }

            // 底部
            // 左闭右开，所以判断循环结束时， j != startY
            for (; j > startY; j--) {
                nums[i][j] = count++;
            }

            // 左列
            // 左闭右开，所以判断循环结束时， i != startX
            for (; i > startX; i--) {
                nums[i][j] = count++;
            }
            startX++;
            startY++;
            offset++;
            loop++;
        }
        if (n % 2 == 1) { // n 为奇数时，单独处理矩阵中心的值
            nums[startX][startY] = count;
        }
        return nums;
    }
}
