package algorithm241012.myarrays;

import java.awt.*;
import java.util.*;
import java.util.List;

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
        HashMap<Integer, Integer> bracket = new HashMap<>(3);
        for (int i = 0; i < len; i++) {
            // 往map中添加元素，使用 getOrDefault 简化逻辑，默认值为 0
            bracket.put(fruits[i], bracket.getOrDefault(fruits[i], 0) + 1);
            // 超过2个了
            while (bracket.size() > 2) {
                bracket.put(fruits[left], bracket.get(fruits[left]) - 1);
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
     * description: TODO 7. lc1004 Given a binary array nums and an integer k, return the maximum number of consecutive 1's
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
        for (int i = 0; i < len; i++) {
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
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = len + 1;
            }
        }
        int index;
        for (int i = 0; i < len; i++) {
            // there should use Math.abs(), because during the process, positive numbers may convert to negative,
            // when next time i point this one, it might be changed
            index = Math.abs(nums[i]);
            // we need to compare the abs() with the len, include len
            if (index <= len) {
                nums[index - 1] = -Math.abs(nums[index - 1]);
            }
        }
        // find the first positive number, and plus one,that's the result
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
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


    /**
     * description: TODO [?,?,1,0,0,?,1] replace ? with the nearest element on the right
     * create time: Dec 19 2024 09:39
     */
    public String replace(String serverType) {
        char[] ch = serverType.toCharArray();
        int size = ch.length;
        int right;
        for (int i = 0; i < size; i++) {
            if (ch[i] == '?') {
                right = i + 1;
                while (right < size && ch[right] == '?') {
                    right++;
                }
                if (right < size) {
                    ch[i] = ch[right];
                } else if (i > 0) {
                    ch[i] = ch[i - 1];
                } else {
                    // 字符串开始处默认替换为 '0'
                    ch[i] = '0';
                }
            }
        }
        return Arrays.toString(ch);
    }

    /**
     * description: TODO 127
     * create time: Dec 19 2024 10:48
     */
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        int slow = 0, fast = slow + 1;
//        for (; slow < len; slow++) {
//            while (fast < len && nums[slow] == nums[fast]) {
//                fast++;
//            }
//            // nums[fast] not equals nums[i]
//            if (fast < len) {
//                nums[slow + 1] = nums[fast];
//            } else {
//                break;
//            }
//        }
        // focus on fast, only traverse once until fast reaches the end
        while (fast < len) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    /**
     * description: TODO 129 lc27 Remove Element
     * create time: Dec 20 2024 09:20
     */
    public int removeElement(int[] nums, int val) {
        int size = nums.length;
        int slow = 0;
        int fast = 0;
        while (fast < size) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    /**
     * description: TODO 130 lc283 Move Zeros
     * Input: nums = [0,1,0,3,12], Output: [1,3,12,0,0]
     * create time: Dec 20 2024 09:31
     */
    public void moveZeroes(int[] nums) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        while (slow < nums.length) {
            nums[slow] = 0;
            slow++;
        }
    }

    /**
     * description: TODO 131 lc438 Find all Anagrams in a String
     * create time: Dec 20 2024 16:36
     */
    public List<Integer> findAnagrams(String s, String p) {
        // 用来记录目标字符串中的字符及其出现次数
        Map<Character, Integer> map = new HashMap<>(16);
        // 用来记录窗口中出现的字符及其次数
        Map<Character, Integer> winEleMap = new HashMap<>(16);
        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0;

        // 初始化目标字符串的频率
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // 边界条件
        if (s.length() < p.length()) {
            return res;
        }

        // 滑动窗口
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;

            // // 如果这个字符在map中有，说明这个元素是要计入 窗口元素是否符合条件的计算中的
            if (map.containsKey(c)) {
                // 将这个字符计入winEleMap
                winEleMap.put(c, winEleMap.getOrDefault(c, 0) + 1);
            }

            // 当窗口包含的元素满足一定条件时，进入到while循环增加left缩小窗口
            // 如果窗口大小超过目标字符串长度，则开始收缩窗口
            while (right - left >= p.length()) {
                if (map.equals(winEleMap)) {
                    res.add(left);
                }
                char temp = s.charAt(left);
                left++;
                // 如果左侧字符在目标字符串中
                if (map.containsKey(temp)) {
                    // 如果频率减少到 0，移除该字符
                    if (winEleMap.get(temp) == 0) {
                        winEleMap.remove(temp);
                    } else {
                        winEleMap.put(temp, winEleMap.get(temp) - 1);
                    }
                }
            }
        }
        return res;
    }


    /**
     * description: TODO 132 lc76 Minimum Window Substring
     * create time: Dec 20 2024 15:36
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>(16);
        Map<Character, Integer> window = new HashMap<>(16);
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        int left = 0, right = 0, min = Integer.MAX_VALUE;
        // valid，记录窗口中有多少字符已经满足了目标字符串的需求
        int valid = 0, start = 0;

        // record appearance times of each letter of string t
        for (char c : cht) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        while (right < s.length()) {
            char c = chs[right];
            right++;
            // if string t contains c, add it into map window
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            // update left
            while (valid == need.size()) {
                if (right - left < min) {
                    min = right - left;
                    start = left;
                }
                char temp = s.charAt(left);
                left++;
                if (need.containsKey(temp)) {
                    if (window.get(temp).equals(need.get(temp))) {
                        valid--;
                    }
                    window.put(temp, window.get(temp) - 1);
                    if (window.get(temp) == 0) {
                        window.remove(temp);
                    }
                }
            }
        }
        return min == Integer.MAX_VALUE ? "" : s.substring(start, start + min);
    }


    /**
     * description: TODO 133 lc567 Permutation in String
     * create time: Dec 21 2024 08:38
     */
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 > len2) {
            return false;
        }
        int right = 0, left = 0, valid = 0;
        Map<Character, Integer> need = new HashMap<>(len1);
        Map<Character, Integer> winEle = new HashMap<>(len1);
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        while (right < len2) {
            char rightc = s2.charAt(right);
            right++;
            if (need.containsKey(rightc)) {
                winEle.put(rightc, winEle.getOrDefault(rightc, 0) + 1);
                if (winEle.get(rightc).equals(need.get(rightc))) {
                    valid++;
                }
            }
            while (left < right && right - left >= len1) {
                char leftc = s2.charAt(left);
                left++;
                if (valid == need.size()) {
                    return true;
                }
                if (need.containsKey(leftc)) {
                    if (winEle.get(leftc).equals(need.get(leftc))) {
                        valid--;
                    }
                    winEle.put(leftc, winEle.get(leftc) - 1);
                    if (winEle.get(leftc) == 0) {
                        winEle.remove(leftc);
                    }
                }
            }
        }
        return false;
    }


    public int solution(int[] D, String[] T) {
        // 定义最远房子的位置
        int farP = -1, farG = -1, farM = -1;

        // 找出每种垃圾的最远房子位置
        for (int i = 0; i < T.length; i++) {
            String temp = T[i];
            if (temp.contains("P")) {
                farP = i;
            }
            if (temp.contains("G")) {
                farG = i;
            }
            if (temp.contains("M")) {
                farM = i;
            }
        }
        // 分别计算每种垃圾车所需时间
        int timeP = calculateTime(D, T, farP, 'P');
        int timeG = calculateTime(D, T, farG, 'G');
        int timeM = calculateTime(D, T, farM, 'M');
        // 返回所需最大时间
        return Math.max(timeP, Math.max(timeG, timeM));
    }

    public int calculateTime(int[] D, String[] T, int index, char type) {
        if (index == -1) {
            // 如果没有这种垃圾类型，时间为0
            return 0;
        }
        int time = 0;
        // 从起点到最远房子，累加收集垃圾的时间
        for (int i = 0; i <= index; i++) {
            // 当前房子有这种垃圾
            if (T[i].indexOf(type) != -1) {
                for (char c : T[i].toCharArray()) {
                    if (c == type) {
                        time++;
                    }
                }
            }
            time += D[i];
        }
        // 加上从最远房子返回到起点的时间
        for (int i = index; i >= 0; i--) {
            time += D[i];
        }
        return time;
    }


    public int solution(String S, int K) {
        // Implement your solution here
        int res = 0, left = 0, right = 0;
        if (K >= S.length()) {
            return res;
        }
        // calculate
        res = calculate(S);
        while (right < S.length()) {
            right++;
            // update left
            while (right - left >= K) {
                String temp = S.substring(0, left) + S.substring(left + K);
                int tempLen = calculate(temp);
                res = Math.min(res, tempLen);
                left++;
            }
        }
        return res;
    }

    public int calculate(String str) {
        StringBuilder compressed = new StringBuilder();
        int n = str.length();
        int count = 1;
        for (int i = 1; i <= n; i++) {
            if (i < n && str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                // 当前字符结束，添加到压缩字符串中
                compressed.append(str.charAt(i - 1));
                if (count > 1) {
                    compressed.append(count);
                }
                // 重置计数
                count = 1;
            }
        }
        return compressed.length();
    }

    /**
     * description: TODO "ABBCC" 压缩为 "A2B2C","AAAA" 压缩为 "A4"
     * create time: Dec 21 2024 22:20
     */
    public String composeStr(String str) {
        StringBuilder sb = new StringBuilder();
        int right = 0, left = 0;
        int len = str.length();
        while (left < len) {
            while (right < len && str.charAt(right) == str.charAt(left)) {
                right++;
            }
            sb.append(str.charAt(left));
            int dis = right - left;
            if (dis > 1) {
                sb.append(dis);
            }
            left = right;
        }
        return sb.toString();
    }


    /**
     * description: TODO 134 lc1 Two Sum
     * create time: Dec 22 2024 12:09
     */
    public int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        int[] res = new int[2];
        Map<Integer, List<Integer>> map = new HashMap<>(len);
        // record elements into a map, key is element, value is index
        for (int i = 0; i < len; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                List<Integer> l = new ArrayList<Integer>();
                l.add(i);
                map.put(nums[i], l);
            }
        }
        for (int i = 0; i < len; i++) {
            // 如果缺的值不是这个值本身
            if (map.containsKey(target - nums[i])) {
                List<Integer> temp = map.get(target - nums[i]);
                // 查看缺的值的下标
                if (temp.size() == 1 && temp.get(0) != i) {
                    res[0] = i + 1;
                    res[1] = temp.get(0) + 1;
                    return res;
                }
                for (int index : temp) {
                    if (i != index) {
                        res[0] = i + 1;
                        res[1] = index + 1;
                        return res;
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int left, len = nums.length, right;
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        int extra;
        if (len < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            extra = -nums[i];
            left = i + 1;
            right = len - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum > extra) {
                    right--;
                } else if (sum < extra) {
                    left++;
                } else {
                    List<Integer> item = new ArrayList<>();
                    item.add(nums[i]);
                    item.add(nums[left]);
                    item.add(nums[right]);
                    res.add(item);
                    left++;
                    right--;
                    while (right > 0 && nums[right] == nums[right + 1]) {
                        right--;
                    }
                    while (left < len && nums[left] == nums[left - 1]) {
                        left++;
                    }
                }
            }
        }
        return res;
    }


    /**
     * description: TODO Lc18 Four Sum
     * create time: Dec 24 2024 10:50
     */
    List<List<Integer>> fourSum(int[] nums, int target) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 4) {
            return res;
        }
        int left, right;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int extra = target - nums[i] - nums[j];
                left = j + 1;
                right = len - 1;
                while (left < right) {
                    int sum = nums[left] + nums[right];
                    if (sum < extra) {
                        left++;
                    } else if (sum > extra) {
                        right++;
                    } else {
                        List<Integer> item = new ArrayList<>();
                        item.add(nums[i]);
                        item.add(nums[j]);
                        item.add(nums[left]);
                        item.add(nums[right]);
                        res.add(item);
                        left++;
                        right--;
                        while (left < len && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (right > 0 && nums[right] == nums[right--]) {
                            right--;
                        }
                    }
                }
            }
        }
        return res;
    }


    public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        int len = s.length();
        int i = 0;
        while (i < len) {
            // reverse i to k
            reverse(ch, i, i + k - 1);
            i = i + 2 * k;
        }
        return String.valueOf(ch);
    }

    public void reverse(char[] ch, int start, int end) {
        if (end >= ch.length) {
            end = ch.length - 1;
        }
        while (start < end) {
            char temp = ch[start];
            ch[start] = ch[end];
            ch[end] = temp;
            start++;
            end--;
        }
    }

    /**
     * description: TODO Input: s = "the sky is blue", Output: "blue is sky the"
     * create time: Jan 04 2025 16:59
     */
    public String reverseWords(String s) {
        int len = s.length();
        StringBuilder sb = new StringBuilder();
        int end = len;
        // remove spaces at the beginning and the end of string
        StringBuilder tempStr = new StringBuilder(s);
        // remove spaces at the beginning
        int index = 0;
        while (tempStr.charAt(index) == ' ') {
            tempStr.deleteCharAt(index);
            index++;
        }
        // remove spaces at the end of string
        index = tempStr.length() - 1;
        while (tempStr.charAt(index) == ' ') {
            tempStr.deleteCharAt(index);
            index--;
        }
        // remove extra spaces in the string
        index = tempStr.length() - 1;


        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                sb.append(s, i + 1, end).append(" ");
                end = i;
            }
            if (i == 0) {
                sb.append(s, i, end);
            }
        }
        return sb.toString();
    }

    public String reverseWords2(String s) {
        int len = s.length();
        StringBuilder word = new StringBuilder();
        StringBuilder res = new StringBuilder();
        for (int i = len - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                word.insert(0, s.charAt(i));
            } else {
                if (!word.isEmpty()) {
                    res.append(word).append(" ");
                    word = new StringBuilder();
                }
            }
        }
        System.out.println(word);
        return res.toString();
    }

    public String reverseWords3(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.insert(0, " ");
        int len = sb.length();
        StringBuilder res = new StringBuilder();
        int flag = -1;
        for (int i = len - 1; i >= 0; i--) {
            if (sb.charAt(i) != ' ') {
                if (flag == -1) {
                    flag = i;
                }
            } else {
                if (flag != -1) {
                    res.append(sb, i + 1, flag + 1).append(" ");
                    flag = -1;
                }
            }
        }
        return res.deleteCharAt(res.length() - 1).toString();
    }

    public String move(String str, int k){
        int len = str.length();
        char[] ch = str.toCharArray();
        for(int i = 0; i < k; i++){
            char c = ch[i];
            if(len - k + i >= len){
                break;
            }
            ch[i] = ch[len - k + i];
            ch[len - k + i] = c;
        }
        return String.valueOf(ch);
    }

    public int[] topKFrequent(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        // compute frequency of each element
        for(int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Comparator<int[]> comparator = new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        };
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(comparator);
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(minHeap.size() >= k){
                minHeap.poll();
            }
            int[] temp = new int[2];
            temp[0] = entry.getKey();
            temp[1] = entry.getValue();
            minHeap.offer(temp);
        }
        int[] res = new int[minHeap.size()];
        int index = 0;
        while(!minHeap.isEmpty()){
            res[index] = minHeap.poll()[0];
            index++;
        }
        return res;
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0;
        while (left < right){
            mid = (left + right) / 2;
            if(target > nums[mid]){
                left = mid + 1;
            } else if(target < nums[mid]){
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return mid;
    }

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        int len = nums.length;
        int left = 0, right = len - 1, mid = 0;
        while(left <= right){
            mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid - 1;
            } else if(nums[mid] < target){
                left = mid + 1;
            } else if(nums[mid] == target){
                res[0] = mid;
                res[1] = mid;
                left = mid + 1;
                right = mid - 1;
                int flag = mid - 1;
                while(flag >= 0 && nums[flag] == target){
                    res[0] = flag;
                    flag--;
                }
                flag = mid;
                while(flag < len && nums[flag] == target){
                    res[1] = flag;
                    flag++;
                }
            }
        }
        return res;
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int left = 0, right = m * n - 1, mid = 0;
        while (left <= right){
            mid = left + (right - left) / 2;
            if(matrix[mid / n][mid % n] == target){
                return true;
            } else if (matrix[mid / n][mid % n] > target){
                right = mid - 1;
            } else if (matrix[mid / n][mid % n] < target){
                left = mid + 1;
            }
        }
        return false;
    }


}
