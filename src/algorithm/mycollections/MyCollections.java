package algorithm.mycollections;

import java.util.*;

/**
 * create by: Ting
 * description: TODO
 * create time: 26/03/2024 15:12
 */
public class MyCollections {


    /**
     * create by: Ting
     * description: TODO Given an array nums of n integers, return an array of all the unique quadruplets
     * https://leetcode.cn/problems/4sum/description/
     * create time: 30/03/2024 13:50
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // from smallest to greatest
        for (int k = 0; k < nums.length; k++) {
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            for (int i = k + 1; i < nums.length; i++) {
                int left, right = nums.length - 1;
                // removing duplicate values of the first number
                if (i > k + 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                left = i + 1;
                while (left < right) {
                    // nums[k] + nums[i] + nums[left] + nums[right] > target int会溢出
                    long sum = (long)nums[k] + nums[i] + nums[left] + nums[right];
                    if (sum < target) {
                        // 可有可无，相当于每一次找left都去找的去重之后的
//                        while (left < right && nums[left] == nums[left + 1]) left++;
                        left++;
                    } else if (sum > target) {
//                        while (left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                    } else {
                        List<Integer> temp = Arrays.asList(nums[k], nums[i], nums[left], nums[right]);
                        res.add(temp);
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                        left++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * create by: Ting
     * description: TODO https://leetcode.cn/problems/3sum/description/
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0
     * create time: 28/03/2024 14:01
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // sorting this array from smallest to largest
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int left, right = nums.length - 1;
            if (nums[i] > 0) {    // minimum value is greater than 0
                return res;
            }
            // removing duplicate values of a
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i + 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    // recording the current values corresponding to i,left and right
                    List<Integer> temp = Arrays.asList(nums[i], nums[left], nums[right]);
                    res.add(temp);
                    // after finding the first ternary,removing duplicate values for b and c
                    while (right > left && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    while (right > left && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    right--;
                    left++;
                }
            }
        }
        return res;
    }

    /**
     * create by: Ting
     * description: TODO Given two strings s and t, return true if t is an anagram of s, and false otherwise.
     * create time: 26/03/2024 15:13
     */
    public boolean isAnagram(String s, String t) {
        int[] record = new int[26]; // there are 26 letters
        char[] charArray = s.toCharArray();
        int a = Integer.valueOf('a');
        for (char c : charArray) {
            int position = Integer.valueOf(c);
            record[position - a] = record[position - a] + 1;
        }
        char[] charArrayT = t.toCharArray();
        for (char c : charArrayT) {
            int position = Integer.valueOf(c);
            record[position - a] = record[position - a] - 1;
        }
        int lengh = record.length;
        for (int i = 0; i < lengh; i++) {
            if (record[i] != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * create by: Ting
     * description: TODO Given two integer arrays nums1 and nums2, return an array of their
     * intersection
     * . Each element in the result must be unique and you may return the result in any order.
     * create time: 26/03/2024 16:12
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> record = new HashSet<>();
        HashSet<Integer> result = new HashSet<>();
        for (int i : nums1) {
            record.add(i);
        }
        for (int i : nums2) {
            if (record.contains(i)) {
                result.add(i);
            }
        }
        int size = result.size();
        int j = 0;
        int[] res = new int[size];
        for (Integer i : result) {
            res[j++] = i;
        }
        return res;
    }


    /**
     * create by: Ting
     * description: TODO Write an algorithm to determine if a number n is happy.
     * create time: 26/03/2024 20:23
     */
    public boolean isHappy(int n) {
        boolean res = true;
        HashSet<Integer> sumSet = new HashSet<>();
        int sum = getSum(n);
        while (sum != 1) {
            if (!sumSet.contains(sum)) {
                sumSet.add(sum);
                sum = getSum(sum);
            } else {
                res = false;
                break;
            }
        }
        return res;
    }

    private int getSum(int n) {
        int res = 0;
        int temp;
        while (n > 0) {
            temp = n % 10;
            res = res + temp * temp;
            n = n / 10;
        }
        return res;
    }


    /**
     * create by: Ting
     * description: TODO https://leetcode.cn/problems/two-sum/submissions/517190729/
     * create time: 27/03/2024 13:28
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashSet<Integer> tempSet = new HashSet<>(); //use HashSet
        int j = 0, temp = 0;
        for (int i : nums) {
            j++;
            temp = target - i;
            if (tempSet.contains(temp)) {
                res[1] = j - 1;
                break;
            }
            tempSet.add(i);
        }
        j = 0;
        for (int i : nums) {
            j++;
            if (i == temp) {
                res[0] = j - 1;
                break;
            }
        }
        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> tempMap = new HashMap<>();
        int j = 0;
        for (int i : nums) {

            int temp = target - i;
            if (tempMap.containsKey(temp)) {
                res[1] = j;
                res[0] = tempMap.get(temp);
                break;
            }
            tempMap.put(i, j);
            j++;
        }
        return res;
    }


    /**
     * create by: Ting
     * description: TODO https://leetcode.cn/problems/ransom-note/description/
     * create time: 27/03/2024 14:58
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<String, Integer> magazineMap = new HashMap<>();
        // 将magazine放到一个map中
        for (char c : magazine.toCharArray()) {
            String s = String.valueOf(c);
            if (magazineMap.containsKey(s)) {
                int temp = magazineMap.get(s) + 1;
                magazineMap.put(s, temp);
            } else {
                magazineMap.put(String.valueOf(c), 1);
            }
        }
        // 遍历ransom字符判断是否都出现在magazine中
        for (char c : ransomNote.toCharArray()) {
            String s = String.valueOf(c);
            if (!magazineMap.containsKey(s)) {
                return false;
            }
            int remain = magazineMap.get(s);
            if (remain <= 0) {
                return false;
            }
            magazineMap.put(s, --remain);
        }
        return true;
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] temp = new int[26];
        // 将magazine放到一个数组中，这个数组记录26个字母出现都频率
        for (char c : magazine.toCharArray()) {
            temp[c - 'a'] = temp[c - 'a'] + 1;
        }
        // 遍历ransom并与temp中都字母做对比
        for (char c : ransomNote.toCharArray()) {
            if (temp[c - 'a'] > 0) {
                temp[c - 'a'] = temp[c - 'a'] - 1;
            } else {
                return false;
            }
        }
        return true;
    }


}
