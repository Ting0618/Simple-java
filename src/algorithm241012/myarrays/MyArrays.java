package algorithm241012.myarrays;

import java.util.*;

/**
 * @author ting
 * create time: Oct 12 2024 23:18
 */

public class MyArrays {
    /**
     * description: TODO 1. binary search
     */
    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1, index;
        while (left <= right) {
            index = (left + right) / 2;
            if (nums[index] == target) {
                return index;
            } else if (nums[index] > target) {
                right = index - 1;
            } else {
                left = index + 1;
            }
        }
        return -1;
    }

    /**
     * description: TODO 2. nums sorted in non-decreasing order,  return an array of the squares of each number
     * sorted in non-decreasing order.
     * create time: Oct 16 2024 21:08
     */
    public int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1;
        int[] res = new int[right + 1];
        int index = right;
        while (left <= right) {
            int left2 = nums[left] * nums[left];
            int right2 = nums[right] * nums[right];
            if (left2 > right2) {
                res[index] = left2;
                left++;
            } else {
                res[index] = right2;
                right--;
            }
            index--;
        }
        return res;
    }


    /**
     * description: TODO 3. 找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组
     * create time: Oct 17 2024 20:58
     */
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            while (sum >= target) {
                min = Math.min(min, i - left + 1);
                sum = sum - nums[left];
                left++;
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * description: TODO 4. 一个数组中小于某个值的最长子串
     * create time: Oct 17 2024 20:37
     */
    public int maxSubArr(int[] nums, int target) {
        int left = 0, len = nums.length, sum = 0, max = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            while (sum >= target && left <= i) {
                sum = sum - nums[left];
                left++;
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    /**
     * description: TODO 5.Fruit Into Baskets
     * create time: Oct 16 2024 22:49
     */
    public int totalFruit2(int[] fruits) {
        int left = 0, len = fruits.length, max = 0;
        HashMap<Integer, Integer> bracket = new HashMap<>();
        for (int i = 0; i < len; i++) {
            // 往map中添加元素，使用 getOrDefault 简化逻辑，默认值为 0
            bracket.put(fruits[i], bracket.getOrDefault(fruits[i], 0) + 1);
            // 超过2个了
            while (bracket.size() > 2) {
                //
                if (bracket.get(fruits[left]) == 0) {
                    bracket.remove(fruits[left]);
                }
                left++;
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    /**
     * description: TODO 6. find the length of the longest substring without repeating characters.
     * create time: Oct 17 2024 22:55
     */
    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int max = 0;
        int len = s.length();
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            // 该元素在前面已经存在了（条件不满足时）
            while (map.containsKey(ch[i]) && left <= i) {
                map.remove(ch[left]);
                left++;
            }
            map.put(ch[i], i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    /**
     * description: TODO 7. Given a binary array nums and an integer k, return the maximum number of consecutive 1's
     * in the array if you can flip at most k 0's.
     * 控制滑动窗口内0的个数不超过k即可
     * create time: Oct 18 2024 20:36
     */
    public int longestOnes(int[] nums, int k) {
        int max = 0, left = 0, len = nums.length, flag = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                flag++;
            }
            //当条件不满足的时候，
            while (flag > k) {
                //想办法将窗口控制到满足条件
                if (nums[left] == 0) {
                    flag--;
                }
                left++;
            }
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

    /**
     * description: TODO 8. 给定一个整数数组 Array，请计算该数组在每个指定区间内元素的总和。
     * create time: Oct 18 2024 21:40
     */
    public int prefSum(int[] nums, int start, int end) {
        int res, len = nums.length, sum = 0;
        int[] sumArr = new int[len];
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            sumArr[i] = sum;
        }
        if (start == 0) return sumArr[end];
        res = sumArr[end] - sumArr[start - 1];
        return res;
    }

    /**
     * description: TODO 28. Container with most water
     * create time: Oct 28 2024 20:07
     */
    public int maxArea(int[] height) {
        int area = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int tempArea = Math.min(height[left], height[right]) * (right - left);
            area = Math.max(area, tempArea);
            if (height[left] >= height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return area;
    }

    /**
     * description: TODO 30. return the total number of subarrays whose sum equals to k.
     * create time: Oct 28 2024 22:29
     */
    public int subarraySum(int[] nums, int k) {
        int res = 0;
        // calculate sum, add sum into a map, key is the sum, value is the occurrence of sum
        Map<Integer, Integer> map = new HashMap<>(16);
        // 1. initialize the map to handle the cases where the first element equals k
        map.put(0, 1);
        int sum = 0;

        // 2. WRONG traverse through can calculate
//        for(int i: nums){
//            sum += i;
//            map.put(sum, map.getOrDefault(sum, 0) + 1);
//        }

        // traverse through nums
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // check (sum - k) if exists in map
            if (map.containsKey(sum - k)) {
                // existing
                res += map.get(sum - k);
            }
            // 2. you have to put it here, because what we find is subarray, we can't include the value that behind i
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    /**
     * description: TODO 31. (lc 56)merge all overlapping intervals,
     * create time: Oct 30 2024 19:33
     */
    public int[][] merge(int[][] intervals) {
        // sorting, by the first element
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        // store arrays that is not overlapping
        List<int[]> merged = new ArrayList<>();
        int len;
        int[] temp;
        // intervals is a 2 dimensional array, so intervals[0] == [3,5],[3,5] is a Array
        for (int[] interval : intervals) {
            if (merged.isEmpty()) {
                merged.add(interval);
            }
            len = merged.size();
            temp = merged.get(len - 1);
            // it's overlapping
            if (interval[0] > temp[1]) {
                merged.add(interval);
            } else {
                interval[1] = Math.max(interval[1], temp[1]);
                merged.get(len - 1)[1] = interval[1];
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    /**
     * description: TODO 31.(lc189) rotate the array to the right by k steps, where k is non-negative.
     * create time: Oct 30 2024 20:33
     * reverse 秒了哈哈哈哈哈
     */
    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    /**
     * description: TODO reverse an array
     */
    public void reverse(int[] arr, int start, int end) {
        int left = start, right = end;
        int temp;
        while (left <= right) {
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * description: TODO 32. (lc238) product of array except self
     * create time: Oct 30 2024 21:08
     * note:Good job, completed independently~~
     * essayer way: use two pointers
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] answers = new int[len];
        // The product of i from left to right, similar to a prefix sum, but here it’s a prefix product
        int[] preProduct = new int[len + 1];
        int[] sufProduct = new int[len + 1];
        for (int i = 0; i < len + 1; i++) {
            if (i == 0) {
                preProduct[i] = 1;
                continue;
            }
            preProduct[i] = preProduct[i - 1] * nums[i - 1];
        }
        // The product of i from right to left, which is a suffix product.
        for (int j = len; j >= 0; j--) {
            if (j == len) {
                sufProduct[j] = 1;
                continue;
            }
            sufProduct[j] = sufProduct[j + 1] * nums[j];
        }
        for(int i = 0; i < len; i++){
            answers[i] = preProduct[i] * sufProduct[i + 1];
        }
        return answers;
    }

    /**
     * description: TODO 33. (lc41) first missing positive
     * create time: Oct 30 2024 22:08
     */
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;
        // convert all negative numbers to (len+1)
        for(int i = 0; i < len; i++){
            if(nums[i] <= 0){
                nums[i] = len + 1;
            }
        }
        int index;
        for(int i = 0; i < len; i++){
            // there should use Math.abs(), because during the process, positive numbers may convert to negative,
            // when next time i point this one, it might be changed
            index = Math.abs(nums[i]);
            // we need to compare the abs() with the len, include len
            if(index <= len){
                nums[index - 1] = -Math.abs(nums[index - 1]);
            }
        }
        // find the first positive number, and plus one,that's the result
        for(int i = 0; i < len; i++){
            if(nums[i] > 0){
                return i + 1;
            }
        }
        return len + 1;
    }

    public static int solution(int S) {
        int res = 0;
        for (int i = 0; i < 10; i++) {
            int sum1 = i;
            if (sum1 > S) {
                continue;
            }
            if (sum1 == S) {
                res++;
                continue;
            }
            for (int j = 0; j < 10; j++) {
                int sum2 = sum1 + j;
                if (sum2 > S) {
                    continue;
                }
                if (sum2 == S) {
                    res++;
                    continue;
                }
                for (int k = 0; k < 10; k++) {
                    int sum3 = sum2 + k;
                    if (sum3 > S) {
                        continue;
                    }
                    if (sum3 == S) {
                        res++;
                        continue;
                    }
                    for (int l = 0; l < 10; l++) {
                        int sum4 = sum3 + l;
                        if (sum4 == S) {
                            res++;
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }
}
