package algorithm241012.mybacktrack;

import java.util.*;

/**
 * @author ting
 */
public class MyBacktrack {
    /**
     * description: TODO 91. lc46 permutation
     * create time: Nov 19 2024 10:35
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        permuteBacktracking(nums, path, used, res);
        return res;
    }

    public void permuteBacktracking(int[] nums, List<Integer> path, boolean[] used, List<List<Integer>> res) {
        // it's time to collect results
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        // make choose
        for (int i = 0; i < nums.length; i++) {
            // if nums[i] was used, choose next one, only exclude used elements
            if (used[i]) {
                continue;
            }
            // nums[i] is unused, prepare parameters
            path.add(nums[i]);
            // Use an array called used to mark which elements have already been used.
            used[i] = true;
            permuteBacktracking(nums, path, used, res);
            // go back the last node, retrieve previous operation, because at this point, root is not the eligible node,
            // is the parent node of the eligible node
            path.removeLast();
            // !!! you forgot to set used[i] = false, why this should be retrieved? if not, when find the first eligible result,
            // all the nums[] are true
            used[i] = false;
        }
    }

    /**
     * description: TODO 92. lc78 Subsets
     * create time: Nov 19 2024 11:24
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        subsetsBacktracking(nums, path, res, 0);
        return res;
    }

    public void subsetsBacktracking(int[] nums, List<Integer> path, List<List<Integer>> res, int start) {
        // we need to record each node
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            subsetsBacktracking(nums, path, res, i + 1);
            path.removeLast();
            // change the value of used[]
        }
        // Once the for loop completes, the program returns to the point of the previous recursive call.
    }


    /**
     * description: TODO 93. lc77 Combination
     * create time: Nov 20 2024 08:55
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        combineBacktracking(n, k, path, res);
        return res;
    }

    public void combineBacktracking(int n, int k, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 1; i <= n; i++) {
            // prepare new parameters
            path.add(i);
            // The count of elements still required: k - path.size()
            // the count of elements still remain: n - (k - path.size()) + 1
            // so here it can be combineBacktracking(i <= n - (k - path.size()) + 1, k, path, res)
            combineBacktracking(n, k, path, res);
            path.removeLast();
        }
    }

    /**
     * description: TODO 94. lc90 Subsets2, existing duplicate element
     * create time: Nov 20 2024 10:10
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        Arrays.sort(nums);
        subsetsWithDupBctrak(nums, path, res, 0);
        return res;
    }

    public void subsetsWithDupBctrak(int[] nums, List<Integer> path, List<List<Integer>> res, int start) {
        // collect each node
        res.add(new ArrayList<>(path));
        // make choose
        for (int i = start; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            subsetsWithDupBctrak(nums, path, res, i + 1);
            path.removeLast();
        }
    }

    /**
     * description: TODO  95. lc40 Combination 2
     * create time: Nov 20 2024 10:49
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        Arrays.sort(candidates);
        combinationSum2Bt(candidates, target, path, 0, res);
        return res;
    }

    public void combinationSum2Bt(int[] candidates, int sum, List<Integer> path, int start, List<List<Integer>> res) {
        if (!path.isEmpty() && sum == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (sum < 0) {
                break;
            }
            path.add(candidates[i]);
            combinationSum2Bt(candidates, sum - candidates[i], path, i + 1, res);
            path.removeLast();
        }
    }

    /**
     * description: TODO 96. lc47, Permutation 2, contain duplicated elements
     * create time: Nov 20 2024 11:35
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     * 输入：nums = [1, 1, 2]
     * 输出： [[1, 1, 2], [1, 2, 1], [2, 1, 1]]
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        permuteUniqueBt(nums, path, res, used);
        return res;
    }

    public void permuteUniqueBt(int[] nums, List<Integer> path, List<List<Integer>> res, boolean[] used) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            permuteUniqueBt(nums, path, res, used);
            used[i] = false;
            path.removeLast();
        }
    }

    /**
     * description: TODO 97. lc39 Combination Sum
     * create time: Nov 20 2024 14:31
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new LinkedList<>();
        combinationSumBt(candidates, target, path, res, 0);
//        combinationSumBt2(candidates, target, path, res);
        return res;
    }

    public void combinationSumBt(int[] candidates, int target, List<Integer> path, List<List<Integer>> res, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) {
            return;
        }
        // make choose
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            combinationSumBt(candidates, target - candidates[i], path, res, i);
            path.removeLast();
        }
    }

    public void combinationSumBt2(int[] candidates, int target, List<Integer> path, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        if (target < 0) {
            return;
        }
        // make choose
        for (int i = 0; i < candidates.length; i++) {
            path.add(candidates[i]);
            combinationSumBt2(candidates, target - candidates[i], path, res);
            path.removeLast();
        }
    }


    /**
     * description: TODO 98. lc216 Combination 3
     * create time: Nov 21 2024 10:35
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        combinationSum3Bt(k, n, path, res, 1);
        return res;
    }

    public void combinationSum3Bt(int k, int n, List<Integer> path, List<List<Integer>> res, int start) {
        if (n == 0 && path.size() == k) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        if (n < 0) {
            return;
        }
        for (int i = start; i <= 9; i++) {
            path.add(i);
            combinationSum3Bt(k, n - i, path, res, i + 1);
            path.removeLast();
        }
    }

    /**
     * description: TODO  99. lc17
     * create time: Nov 21 2024 10:59
     */
    public List<String> letterCombinations(String digits) {
        String[] str = {" ", " ", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder path = new StringBuilder();
        List<String> res = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }
        letterCombinationsBt(str, digits, path, res, 0);
        return res;
    }

    public void letterCombinationsBt(String[] str, String digits, StringBuilder path, List<String> res, int cur) {
        if (path.length() == digits.length()) {
            res.add(new String(path));
            return;
        }
        // make choose
        char curNumber = digits.charAt(cur);
        String curLetters = str[curNumber - '0'];
        for (int i = 0; i < curLetters.length(); i++) {
            path.append(curLetters.charAt(i));
            letterCombinationsBt(str, digits, path, res, cur + 1);
            path.deleteCharAt(path.length() - 1);
        }

    }

    /**
     * description: TODO 100. lc131 possible palindrome partitioning
     * create time: Nov 21 2024 12:30
     */
    public List<List<String>> partition(String s) {
        List<String> path = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        partitionBt(s, path, res, 0);
        return res;
    }

    public void partitionBt(String s, List<String> path, List<List<String>> res, int start) {
        // collect results
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        //
        for (int i = start; i < s.length(); i++) {
            if (!isPalindrome(s, start, i)) {
                continue;
            }
            // if from start to i is palindrome
            path.add(s.substring(start, i + 1));
            partitionBt(s, path, res, i + 1);
            path.removeLast();
        }
    }

    boolean isPalindrome(String s, int lo, int hi) {
        // use two pointers to determine if s is a palindrome
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) {
                return false;
            }
            lo++;
            hi--;
        }
        return true;
    }

    /**
     * description: TODO 101. lc93 Restore IP address
     * create time: Nov 22 2024 09:54
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        List<String> path = new ArrayList<>();
        restoreIpAddressesBt(s, 0, path, res);
        return res;
    }

    public void restoreIpAddressesBt(String s, int start, List<String> path, List<String> res) {
        if (path.size() == 4 && start == s.length()) {
            StringBuilder ss = new StringBuilder();
            for (int i = 0; i < path.size() - 1; i++) {
                ss.append(path.get(i)).append(".");
            }
            ss.append(path.get(path.size() - 1));
            res.add(ss.toString());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String temp = s.substring(start, i + 1);
            // if starts with 0 but don't include 0
            if (temp.length() > 1 && temp.charAt(0) == '0') {
                break;
            }
            if (Integer.parseInt(temp) > 255) {
                break;
            }
            if (path.size() > 4) {
                break;
            }
            path.add(temp);
            restoreIpAddressesBt(s, i + 1, path, res);
            path.removeLast();
        }
    }

    /**
     * description: TODO 102. lc491 Non-decreasing Subsequences
     * create time: Nov 22 2024 10:52
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        findSubsequences2(nums, path, res, 0);
        return res;
    }

    public void findSubsequences2(int[] nums, List<Integer> path, List<List<Integer>> res, int start) {
        if (path.size() > 1) {
            res.add(new ArrayList<>(path));
//            return;
        }
        // 用于记录当前层已经选择过的数字
        Set<Integer> used = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
//            if(i > start && nums[i] <= nums[i - 1]){
//                continue;
//            }
            // 去重：当前层中已使用过的数字跳过
            if (used.contains(nums[i])) {
                continue;
            }
            if (!path.isEmpty() && path.get(path.size() - 1) > nums[i]) {
                continue;
            }
            path.add(nums[i]);
            // 标记当前数字为已使用
            used.add(nums[i]);
            findSubsequences2(nums, path, res, i + 1);
            path.removeLast();
        }
    }

}
