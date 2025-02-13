package algorithm241012.myhash;


import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ting
 */
public class MyHash {
    /**
     * description: TODO 19.Valid Anagram
     * create time: Oct 23 2024 19:58
     */
    public boolean isAnagram(String s, String t) {
        int[] record = new int[26];
        char[] chs = s.toCharArray();
        char[] cht = t.toCharArray();
        char temp;
        // put s into record, a's ascll is 97, z is 122
        for (char ch : chs) {
            temp = ch;
            record[(temp - 'a')] += 1;
        }
        int num;
        // go through t, check if every character exists in s
        for (char c : cht) {
            temp = c;
            num = record[(temp - 'a')];
            if (num == 0) return false;
            record[(temp - 'a')] -= 1;
        }
        // check array record, if there are non-zero values
        for (int c : record) {
            if (c != 0) return false;
        }
        return true;
    }

    /**
     * description: TODO 20.subset
     * create time: Oct 23 2024 20:40
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // add nums1 to a set
        Set<Integer> nums1Set = new HashSet<>();
        Set<Integer> nums2Set = new HashSet<>();
        List<Integer> resList = new ArrayList<>();
        for (int i : nums1) {
            nums1Set.add(i);
        }
        // add nums2 to another set
        for (int i : nums2) {
            nums2Set.add(i);
        }
        // use a list to collect the subset
        for (Integer i : nums1Set) {
            if (nums2Set.contains(i)) {
                resList.add(i);
            }
        }
        // ture list to an array
        int[] res = new int[resList.size()];
        int j = 0;
        for (int i : resList) {
            res[j] = i;
            j++;
        }
        return res;
    }

    /**
     * description: TODO 21.Given an array of integers nums and an integer target,
     * return indices of the two numbers such that they add up to target.
     * create time: Oct 24 2024 21:08
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(16);
        int temp;
        int[] res = new int[2];
        // 往map存值，是需要一个过程的，加入nums = [2,0,11,15，7]，target=9，在2的时候map中找不到7，但是我们只要在7的时候能找到2就行
        for (int i = 0; i < nums.length; i++) {
            temp = target - nums[i];
            if (map.containsKey(temp)) {
                res[0] = i;
                res[1] = map.get(temp);
                return res;
            }
            // don't understand why we can check and store the values in the map at the same time
            map.put(nums[i], i);
        }
        return res;
    }

    /**
     * description: TODO 22.4Sum II
     * create time: Oct 24 2024 21:42
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 1. 理解清楚题意，相当于是每个数组里面取一个，然后相加得0有几种情况，A[i] + B[j] + C[k] + D[l] == 0。
        // A[i] + C[k] + D[l] + B[j] == 0 这种情况和刚刚的是一样的
        Map<Integer, Integer> map = new HashMap<>(16);
        // the return value
        int res = 0;
        // store all A[i]+ B[j] in a map, key is the sum, value is how many times the sum appears
        for (int k : nums1) {
            for (int i : nums2) {
                // 2. don't understand why value is the appearance times of the same sum
                // value == 2, means there are two pairs of nums that the sum is key
                map.put(k + i, map.getOrDefault(k + i, 0) + 1);
            }
        }
        // traverse nums3 and nums4, if c + d == - (a + b),means exist
        for(int c: nums3){
            for(int d: nums4){
                if(map.containsKey(-(c + d))){
                    // 3. here is not only simply res++, cause the sum of a + b has Value types of arrangement
                    // cause "res" is the occurrence of nums that sum up to 0,
                    res += map.get(-(c+d));
                }
            }
        }
        return res;
    }

    /**
     * description: TODO 23.return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
     * create time: Oct 24 2024 22:10
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map = new HashMap<>(16);
        // store every char of magazine in a map, values are the number of the char
        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            // it will throw an error if written like this: map.put(c, map.get(c) + 1), cause Map.get(Object) is null
        }
        // every word in magazine can be use only once
        for (char c : ransomNote.toCharArray()) {
            if (map.containsKey(c) && map.get(c) != 0) {
                map.put(c, map.get(c) - 1);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * description: TODO 24.3Sum
     * create time: Oct 25 2024 22:55
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // sort nums in ascending order
        Arrays.sort(nums);
        // define left and right
        int left, right;
        int tempSum;
        // store result
        List<List<Integer>> res = new ArrayList<>();
        // traverse nums to see if every i can find two numbers which are left and right that sum to 0
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            left = i + 1;
            right = nums.length - 1;
            while(left < right){
                // the sum of three
                tempSum = nums[i] + nums[left] + nums[right];
                if (tempSum == 0){
                    // 1. every time we need to store a different List to res, so we need a new resValue
                    List<Integer> resValues = new ArrayList<>();
                    resValues.add(nums[i]);
                    resValues.add(nums[left]);
                    resValues.add(nums[right]);
                    res.add(resValues);
                    // After finding a triplet, remove duplicates for b and c
                    // 3. we need to drop the duplicate values, note: right + 1, why?
                    while(nums[right] == nums[right - 1] && left < right){
                        right--;
                    }
                    // 4. the same, but why?
                    // 5. why left < right? cause what if all nums are the same? left can add to maximum right, right can
                    // minus to minimum left
                    while(nums[left] == nums[left + 1] && left < right){
                        left++;
                    }
                    // 2. if left and right meet the condition, we need to change both left and right, otherwise it will
                    // go into a infinite loop
                    right--;
                    left++;
                }else if(tempSum > 0){
                    // which means right is too big to sum to 0, so we move right index one step to the left(right--)
                    right--;
                }else {
                    left++;
                }
            }
        }
        return res;
    }

    /**
     * description: TODO  25. 4Sum 2
     * create time: Oct 27 2024 18:46
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        int left, right;
        long sum;
        List<List<Integer>> res = new ArrayList<>();
        // sort the array nums
        Arrays.sort(nums);
        // use two for and a left and a right pointer
        for(int i = 0; i < nums.length; i++){
            // all values are bigger than target
            if(nums[i] > target && nums[i] >= 0){
                break;
            }
            // remove the duplication
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            for(int j = i + 1; j < nums.length - 1; j++){
                if (nums[j] + nums[i] > target && nums[j] + nums[i] >= 0) {
                    break;
                }
                // remove the duplication
                if(j > i + 1 && nums[j] == nums[j - 1]){
                    continue;
                }
                left = j + 1;
                right = nums.length - 1;
                while(left < right){
                    sum = (long)nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target){
                        List<Integer> val = new ArrayList<>();
                        val.add(nums[i]);
                        val.add(nums[j]);
                        val.add(nums[left]);
                        val.add(nums[right]);
                        res.add(val);
                        // remove the duplications in left and right
                        while (left < right && nums[left] == nums[left + 1]){
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]){
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum > target) {
                        right--;
                    }else {
                        left++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * description: TODO 27.return the length of the longest consecutive elements sequence.
     * create time: Oct 27 2024 22:48
     * note: check if nums[i] is the start of a sequence(check if nums[i] - 1 exist)
     */
    public int longestConsecutive(int[] nums) {
        int longest = 0;
        int currentNum;
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // add all nums into a set
        Set<Integer> numSet = new HashSet<>();
        for(int i: nums){
            numSet.add(i);
        }
        // check if it's consecutive
        for(int i = 0; i < nums.length; i++){
            // To keep a record of how long each new sequence can go.
            int currentLength = 0;
            // check if nums[i] is the start of a sequence(check if nums[i] - 1 exist)
            // if it is the start
            if(!numSet.contains(nums[i] - 1)){
                currentNum = nums[i];
                currentLength++;
                while (numSet.contains(currentNum + 1)){
                    currentNum++;
                    currentLength++;
                }
                longest = Math.max(longest, currentLength);
            }
        }
        return longest;
    }
}
