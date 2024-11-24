package algorithm241012.monotonicstacks;

import java.util.*;

public class MyMonotonicStacks {
    /**
     * description: TODO 34. (lc793)dailyTemperatures
     * create time: Oct 31 2024 20:17
     */
    public int[] dailyTemperatures(int[] temperatures) {
        // it is a monotonic decreasing stack, 1. why Deque?
        Deque<Integer> decreaStack = new LinkedList<>();
        int[] res = new int[temperatures.length];
        int temp;
        for (int i = 0; i < temperatures.length; i++) {
            // note: element temperatures[i] no matter what, must add into the stack, if the element don't match the stack,
            // then, the other element of the stack out ~~~
            // 2. why while? why temperatures[decreaStack.peek()]? why "<"
            while (!decreaStack.isEmpty() && temperatures[decreaStack.peek()] < temperatures[i]) {
                temp = decreaStack.pop();
                // 3. why res[temp] rather res[i]?
                res[temp] = i - temp;
            }
            // no matter what, each element of temperatures will be added into stack
            decreaStack.push(i);
        }
        return res;
    }

    /**
     * description: TODO 35. (lc496)Next greater element1
     * create time: Oct 31 2024 21:41
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int[] ans = new int[len];
        Deque<Integer> stack = new LinkedList<>();
        // key is the element of nums2, value is the next greater value of element
        Map<Integer, Integer> map = new HashMap<>();
        // initialize ans
        Arrays.fill(ans, -1);
        int popIndex;
        // find the next greater value of each element in nums2, and put it into a map
        for (int i = 0; i < nums2.length; i++) {
            // Pay attention to the symbols here，< > = ?
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                popIndex = stack.pop();
                map.put(nums2[popIndex], nums2[i]);
            }
            stack.push(i);
        }
        // if after traversing, there still are some elements stayed in stack,
        // if we don't do anything, the map won't include these elements
        while (!stack.isEmpty()) {
            map.put(nums2[stack.pop()], -1);
        }
        // traverse nums1 to find the appropriate j
        int nextGreaterValue;
        for (int i = 0; i < len; i++) {
            nextGreaterValue = map.get(nums1[i]);
            if (nextGreaterValue != -1) {
                ans[i] = nextGreaterValue;
            }
        }
        return ans;
    }

    /**
     * description: TODO 36. (lc503)next greater number2
     * create time: Nov 01 2024 22:16
     */
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        // it's a decreasing stack
        Deque<Integer> stack = new LinkedList<>();
        int[] res = new int[len];
        // initialize res to -1
        Arrays.fill(res, -1);
        // traverse through nums to find the next greater
        // because it's a circular array, we can add two array together, but due to wo only use the index of the array
        // so we Just take the index modulo during processing.
        int index, preIndex;
        for (int i = 0; i < len * 2; i++) {
            // pay attention that the array contains duplicated values
            index = i % len;
            // we are going to find the next greater
            while (!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                preIndex = stack.pop();
                res[preIndex] = nums[index];
            }
            stack.push(index);
        }
        // when the code reaches this point,
        // there are some elements remained in stack, and you don't handle the duplicated values
        return res;
    }

    /**
     * description: TODO 37. (lc 42)Trapping rain water --- Two pointers
     * create time: Nov 02 2024 21:13
     */
    public int trap(int[] height) {
        int len = height.length;
        if (len <= 2) {
            return 0;
        }
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        int areaSum = 0;
        // record maximum value to the left of each element
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            // only need to compare i-1 and i, because this array records the maximum value
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // record the maximum value to the right of each element
        rightMax[len - 1] = height[len - 1];
        for (int j = len - 2; j > -1; j--) {
            rightMax[j] = Math.max(rightMax[j + 1], height[j]);
        }
        // calculate the sum of area
        int area = 0;
        for (int i = 0; i < len; i++) {
            area = Math.min(leftMax[i], rightMax[i]) - height[i];
            if (area > 0) {
                areaSum += area;
            }
        }
        return areaSum;
    }

    /**
     * description: TODO 38. (lc 42)Trapping rain water --- monotonic stack
     * create time: Nov 03 2024 09:16
     */
    public int trapWater(int[] height) {
        // maintain a decreasing monotonic stack, because when right > left, we need to calculate the area
        Deque<Integer> stack = new LinkedList<>();
        int sum = 0, curHeight, curWidth;
        int len = height.length;
        int lowest, leftIndex;
        for (int i = 0; i < len; i++) {
            // it's time to calculate
            // notice, there is <=
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                lowest = stack.pop();
                // at this point the stack might be empty
                if (stack.isEmpty()) {
                    break;
                }
                // calculate by height * width
                // if is two repeated elements, don't execute these code
                if (height[lowest] != height[i]) {
                    leftIndex = stack.peek();
                    curHeight = Math.min(height[i], height[leftIndex]) - height[lowest];
                    curWidth = i - leftIndex - 1;
                    sum = sum + curHeight * curWidth;
                }
            }
            stack.push(i);
        }
        return sum;
    }

    /**
     * description: TODO 39. (lc84) Largest Rectangle Histogram
     * create time: Nov 03 2024 10:45
     */
    public int largestRectangleArea(int[] heights) {
        // For each bar in the histogram, the largest rectangle that includes this bar
        // is limited by the nearest shorter bar to its left and the nearest shorter bar to its right.
        // use a increasing monotonic stack

        return 0;
    }

    /**
     * description: TODO 40. (lc239)sliding window maximum
     * create time: Nov 03 2024 11:51
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // record the maximum element of a window, a monotonic stack
        Deque<Integer> stack = new LinkedList<>();
        int len = nums.length;
        int[] res = new int[len];
        int left;
        // initialize stack
        for(int i = 0; i < k; i++){

        }
        return null;
    }
}
