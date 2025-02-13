package algorithm241012.coding;


import algorithm241012.mytree.TreeNode;

import java.util.*;

/**
 * @author ting
 */
public class Whatever {
    /**
     * description: TODO traverse a binary tree using iterative approach (preorder)
     * create time: Jan 05 2025 20:09
     */
    public List<Integer> preorder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()){
            res.add(stack.pop().val);
            if(root.left != null){
                stack.push(root.left);
            }
            if(root.right != null){
                stack.push(root.right);
            }
        }
        return res;
    }

    /**
     * description: TODO preorder approach
     * create time: Jan 05 2025 22:34
     */
    int result = 0;
    public void maxDepth(TreeNode root, int maxDeep){
        if(root == null){
            return;
        }
        maxDeep++;
        result = Math.max(result, maxDeep);
        maxDepth(root.left, maxDeep);
        maxDepth(root.right, maxDeep);
    }
    public int getMaxDepth(TreeNode root){
        maxDepth(root, 0);
        return result;
    }

    int maxDeep = 0;
    public void maxDepth(TreeNode root){
        if(root == null){
            return;
        }
        maxDeep++;
        result = Math.max(result, maxDeep);
        maxDepth(root.left);
        maxDepth(root.right);
        maxDeep--;
    }

    /**
     * description: TODO find max depth by postorder
     * create time: Jan 05 2025 22:49
     */
    public int postMaxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = postMaxDepth(root.left);
        int right = postMaxDepth(root.right);
        // current root position
        return Math.max(left, right) + 1;
    }

    /**
     * description: TODO print the number of layer of each node
     * create time: Jan 06 2025 00:19
     */
    public void printLayer(TreeNode root, int depth){
        if(root == null){
            return;
        }
        System.out.println(depth + " ");
        depth++;
        printLayer(root.left, depth);
        printLayer(root.right, depth);
    }
    /**
     * description: TODO print subtrees of each node
     * create time: Jan 07 2025 22:12
     */
    public int printSubtrees(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = printSubtrees(root.left);
        int right = printSubtrees(root.left);
        System.out.println(left + right + 1);
        return left + right + 1;
    }

    /**
     * description: TODO find the minimum depth, level traverse
     * create time: Jan 07 2025 22:12
     */
    public int findMinimum1(TreeNode root){
        if(root == null){
            return 0;
        }
        // define a queue which is used to store each layer's element
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;
        int size = queue.size();
        while(!queue.isEmpty()){
            depth++;
            TreeNode temp = queue.poll();
            while (size > 0){
                size--;
                if(temp.left == null && temp.right == null){
                    return depth;
                }
                if(temp.left != null){
                    queue.offer(temp.left);
                }
                if(temp.right != null){
                    queue.offer(temp.right);
                }
            }

        }
        return depth;
    }

    /**
     * description: TODO find the minimum depth, postorder
     */
    public int findMinimum2(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = findMinimum2(root.left);
        int right = findMinimum2(root.right);
        if(root.left == null && root.right != null){
            return right + 1;
        }
        if(root.left != null && root.right == null){
            return left + 1;
        }
        return Math.min(left, right) + 1;
    }
    /**
     * description: TODO find the minimum depth, preorder
     */
    int findMinimum3Res = Integer.MAX_VALUE;
    public void findMinimum3(TreeNode root, int depth){
        if(root == null){
            return;
        }
        depth++;
        // left node
        if(root.left == null && root.right == null){
            findMinimum3Res = Math.min(findMinimum3Res, depth);
        }
        findMinimum3(root.left, depth);
        // 为什么这里不需要回溯？
        findMinimum3(root.right, depth);
        // 这里也不需要，但是为什么呢
    }

    public int sumOfLeftLeaves(TreeNode root) {
        return leftDfs(root);
    }
    public int leftDfs(TreeNode root){
        if(root == null){
            return 0;
        }
        int left = leftDfs(root.left);
        int right = leftDfs(root.right);
        int mid = 0;
        if(root.left != null && root.left.left == null && root.left.right == null){
            mid = root.left.val;
        }
        return left + right + mid;
    }


    /**
     * @Description TODO built a tree according to its preorder and inorder
     * @Date 2025/1/21 19:10
     **/
    public TreeNode built(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){
        // left closed, right closed
        if(preStart < 0 || preEnd > preorder.length - 1 || inStart < 0 || inEnd > inorder.length - 1){
            return null;
        }

        // current root
        int val = preorder[preStart];
        // find the index of val in inorder traversal
        int index = -1;
        for(int i = inStart; i <= inEnd; i++){
            if(inorder[i] == val){
                index = i;
                break;
            }
        }
        int leftNodes = index - inStart;
        TreeNode root = new TreeNode(val);
        root.left = built(preorder, preStart + 1, preStart + leftNodes, inorder, inStart, index - 1);
        root.right = built(preorder, preStart + leftNodes + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }

    public int removeDuplicates(int[] nums) {
        int left = 1, len = nums.length;
        for(int right = 1; right < len; right++){
            if(nums[right - 1] != nums[right]){
                nums[left] = nums[right];
                left++;
            }
        }
        return left;
    }

    public String longestPalindrome(String s){
        String res = new String();
        int max = 0;
        for(int i = 0; i < s.length(); i++){
            for(int j = i + 1; j < s.length(); j++){
                String sub = s.substring(i, j + 1);
                // check if sub is a palindrome
                boolean isPa = true;
                int left = i, right = j;
                while(left < right){
                    if(s.charAt(left) != s.charAt(right)){
                        isPa = false;
                        break;
                    }
                    left++;
                    right--;
                }
                if(isPa && sub.length() > max){
                    max = sub.length();
                    res = sub;
                }
            }
        }
        return res;
    }

    public String expandAroundCenter(String s, int center1, int center2){
        while (center1 >= 0 && center2 < s.length()){
            if (s.charAt(center1) == s.charAt(center2)){
                center1--;
                center2++;
            } else {
                break;
            }
        }
        return s.substring(center1 + 1, center2);
    }


    public String minWindow(String s, String t) {
        if(s.isEmpty()){
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0, len = s.length();
        int min = Integer.MAX_VALUE;
        // 用来表示窗口中有几个满足条件的字符
        int valid = 0, start = 0;
        // put characters of t into a map, value represents the occurrence of the character
        for(int i = 0; i < t.length(); i++){
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        while(right < len){
            char chRight = s.charAt(right);
            right++;
            // if this character is present in t, put it into window
            if(map.containsKey(chRight)){
                window.put(chRight, window.getOrDefault(chRight, 0) + 1);
                if(window.get(chRight).equals(map.get(chRight))){
                    valid++;
                }
            }
            // check window, whether it need to shrink
            while(valid == map.size()){
                char chLeft = s.charAt(left);
                if(right - left < min){
                    min = right - left;
                    start = left;
                }
                // if the left character is present in t, shrink window
                if (map.containsKey(chLeft)) {
                    window.put(chLeft, window.get(chLeft) - 1);
                    if(window.get(chLeft) < map.get(chLeft)){
                        valid--;
                    }
                    if (window.get(chLeft) == 0) {
                        window.remove(chLeft);
                    }
                }
                left++;
            }
        }
        return min == Integer.MAX_VALUE? "": s.substring(start, start + min);
    }

    public boolean involve(Map<Character, Integer> window,Map<Character, Integer> map){
        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            char temp = entry.getKey();
            if(!window.containsKey(temp) || window.get(temp) < entry.getValue()){
                return false;
            }
        }
        return true;
    }


    public int totalFruit(int[] fruits) {
        if(fruits.length < 1){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, right = 0, len = fruits.length;
        int res = 0;
        while(right < len){
            int r = fruits[right];
            right++;
            map.put(r, map.getOrDefault(r, 0) + 1);
            while(left < right && map.size() > 2){
                int l = fruits[left];
                if(map.get(l) > 1){
                    map.put(l, map.get(l) - 1);
                } else {
                    map.remove(l);
                }
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }


    /**
     * @Description TODO 判断固定窗口内是否存在重复字符串
     * @Date 2025/1/28 09:59
     **/
    public List<String> findString(String s, int k){
        List<String> res = new ArrayList<>();
        if(s.isEmpty()){
            return res;
        }
        int left = 0, right = 0, len = s.length();
        Map<Character, Integer> window = new HashMap<>();
        while (right < len){
            char cr = s.charAt(right);
            right++;
            window.put(cr, window.getOrDefault(cr, 0) + 1);
            // it is time to move left
            while (right - left > k || window.get(cr) > 1){
                char cl = s.charAt(left);
                window.put(cl, window.get(cl) - 1);
                left++;
            }
            if(right - left == k){
                res.add(s.substring(left, right));
            }
        }
        return res;
    }

    public int characterReplacement(String s, int k) {
        if (s.isEmpty()) {
            return 0;
        }
        int left = 0, right = 0, res = 0, maxFreq = 0;
        // 用于记录每个字符的频率
        Map<Character, Integer> window = new HashMap<>();
        while (right < s.length()) {
            char cr = s.charAt(right);
            right++;
            // 增加当前字符的频率
            window.put(cr, window.getOrDefault(cr, 0) + 1);
            // 更新窗口内最多字符的频率
            maxFreq = Math.max(maxFreq, window.get(cr));
            // 判断是否需要缩小窗口
            while (right - left - maxFreq > k) {
                char cl = s.charAt(left);
                // 减少窗口左侧字符的频率
                window.put(cl, window.get(cl) - 1);
                if (window.get(cl) == 0) {
                    window.remove(cl);
                }
                left++;
            }
            // 更新结果
            res = Math.max(res, right - left);
        }
        return res;
    }

    public int removeDuplicates2(int[] nums) {
        int mark = 0, slow = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] == nums[i - 1]){
                mark++;
            }
            if(mark <= 2){
                nums[slow] = nums[i];
                slow++;
            }
            if(mark > 2){
                mark = 1;
            }
        }
        return slow;
    }

    public void backtracking(String s, int start, List<String> path, List<List<String>> res){
        if(start >= s.length()){
            res.add(new ArrayList<String>(path));
        }
        for(int i = start; i < s.length(); i++){
            if(!isHui(s.substring(start, i + 1))){
                continue;
            }
            path.add(s.substring(start, i + 1));
            backtracking(s, i + 1, path, res);
            path.removeLast();
        }
    }
    public boolean isHui(String str){
        if(str.isEmpty()){
            return false;
        }
        int len = str.length();
        int left = 0, right = len - 1;
        while(left < right){
            if(str.charAt(left) != str.charAt(right)){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
