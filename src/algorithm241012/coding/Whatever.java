package algorithm241012.coding;


import algorithm241012.mylist.LinkNode;
import algorithm241012.mytree.TreeNode;

import javax.xml.stream.events.Characters;
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

    public void backtrack(String s, int start, StringBuilder path, List<String> res){
        if(start == s.length()){
            path.deleteCharAt(path.length() - 1);
            res.add(path.toString());
        }
        for(int i = start; i < s.length(); i++){
            if(i - start >= 3) break;
            String sub = s.substring(start, i + 1);
            if(sub.charAt(0) == '0'){
                continue;
            }
            path.append(s, start, i + 1).append(".");
            backtrack(s, i + 1, path, res);
            path.deleteCharAt(path.length() - 1);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, path, res);
        return res;
    }
    public void backtrack(int[] nums, int start, List<Integer> path, List<List<Integer>> res){
        if(path.size() >= 2){
            res.add(new ArrayList<>(path));
            // If return is used, the function will stop early and always output two numbers.
            // return;
        }
        Set<Integer> set = new HashSet<>();
        for(int i = start; i < nums.length; i++){
            // this approach is incorrect, if the array is sorted, we can compare adjacent elements to remove duplicates
            // at the same tree level, but what if the array is not sorted?
            // if(i > start && nums[i] <= nums[i - 1]){
            //     continue;
            // }

            // if the array is not sorted, how to avoid using duplicates? -- using a hashset to remove duplicates at
            // the tree level
            if(set.contains(nums[i])){
                continue;
            }
            // the selection range for the next tree level should be smaller than those in the previous tree level,
            // how to compare values between different tree levels? -- compare with the value in the path,
            // nums[i] and nums[i - 1] can only be compared within the same tree level
            if(!path.isEmpty() && path.getLast() > nums[i]){
                continue;
            }
            path.add(nums[i]);
            set.add(nums[i]);
            backtrack(nums, i + 1, path, res);
            path.removeLast();
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, used, path, res);
        return res;
    }
    public void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res){
        if(path.size() == nums.length){
            res.add(new ArrayList<>(path));
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i]){
                continue;
            }
            if(i > 0 && nums[i] == nums[i - 1] && !used[i - 1]){
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backtrack(nums, used, path, res);
            path.removeLast();
            used[i] = false;
        }
    }

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        // find the cycle position
        int index = -1;
        for(int i = 0; i < len; i++){
            if(map.containsKey(nums[i])){
                index = map.get(nums[i]);
                break;
            }
            map.put(nums[i], i);
        }
        int arrLen = len - index - 1 + len;
        int[] arr = new int[arrLen];
        // set arr's value
        for(int i = 0; i < arrLen; i++){
            if(i >= len - 1 && map.containsKey(nums[i - len + index])){
                arr[i] = nums[map.get(nums[i - len + index])];
            } else {
                arr[i] = nums[i];
                map.put(nums[i], i);
            }
        }
        return arr;
    }


    int minutes = 0;
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<List<Integer>> rotting = new ArrayList<>();
        // find all rotting oranges
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 2){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    rotting.add(temp);
                }
            }
        }
        bfs(grid, rotting);
        // to find if fresh pranges exist
        for (int[] ints : grid) {
            for (int j = 0; j < n; j++) {
                if (ints[j] == 1) {
                    return -1;
                }
            }
        }
        return minutes == 0? 0:minutes - 1;
    }
    public void bfs(int[][] grid, List<List<Integer>> allBad){
        Deque<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        for(List<Integer> bad: allBad){
            int[] item = new int[2];
            item[0] = bad.get(0);
            item[1] = bad.get(1);
            queue.offer(item);
        }
        int size = 0;
        // 方向数组（上、下、左、右）
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while(!queue.isEmpty()){
            size = queue.size();
            for(int k = 0; k < size; k++){
                int[] item = queue.poll();
                int i = item[0];
                int j = item[1];
                for(int[] dire: directions){
                    int newX = i + dire[0];
                    int newY = j + dire[1];
                    if(newX >= 0 && newY >= 0 && newX < m && newY < n && grid[newX][newY] == 1){
                        grid[newX][newY] = 2;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
            minutes++;
        }
    }


    public boolean validTree(int n, int[][] edges) {
        int[][] graph = buildGraph(n, edges);
        boolean[] used = new boolean[n];
        // 1. check if has cycle
        boolean hasCycle = hasCycle(graph, used);
        // 2. count the number of connected components in the graph.
        if(hasCycle){
            return false;
        }
        for(boolean use: used){
            if(!use){
                return false;
            }
        }
        return true;
    }
    // create a graph
    public int[][] buildGraph(int n, int[][] edges){
        int[][] graph = new int[n][n];
        for(int[] edge: edges){
            int from = edge[0];
            int to = edge[1];
            graph[from][to] = 1;
            graph[to][from] = 1;
        }
        return graph;
    }
    public boolean hasCycle(int[][] graph, boolean[] used){
        int n = graph.length;
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(0);
        used[0] = true;
        while(!queue.isEmpty()){
            int top = queue.poll();
            for(int i = 0; i < n; i++){
                if(graph[top][i] == 1){
                    if(used[i]){
                        return true;
                    }
                    queue.offer(i);
                    used[i] = true;
                }
            }
        }
        return false;
    }


    public boolean isAlienSorted(String[] words, String order) {
        // use a map to store its order
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < order.length(); i++){
            char c = order.charAt(i);
            map.put(c, i + 1);
        }
        int previous = 0;
        for(String word: words){
            for(char c: word.toCharArray()){
                int or = map.get(c);
                if(or < previous){
                    return false;
                }
                previous = or;
            }
            previous = 0;
        }
        return true;
    }

    public int longestConsecutive(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        // put all elements into a map<value, index>
        Set<Integer> set = new HashSet<>();
        int len = nums.length;
        for (int j : nums) {
            set.add(j);
        }
        // if nums[i] is not the midle number in the sequence
        int maxLen = 0, num = 0, res = 0;
        for (int j : set) {
            num = j;
            maxLen = 1;
            if (!set.contains(num - 1)) {
                while (set.contains(num + 1)) {
                    maxLen++;
                    num++;
                }
                res = Math.max(maxLen, res);
            }
        }
        return res;
    }

    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        visited = new boolean[m][n];
        boolean res = false;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(board[i][j] == word.charAt(0)){
                    res = search(board, word, i, j, 0);
                    if(res){
                        return res;
                    }
                }
            }
        }
        return res;
    }
    public boolean search(char[][] board, String word, int i, int j, int index){
        int m = board.length;
        int n = board[0].length;
        if(i < 0 || j < 0 || i >= m || j >= n){
            return false;
        }
        if(board[i][j] != word.charAt(index)){
            return false;
        }
        if(index == word.length() - 1){
            return true;
        }
        board[i][j] = '1';
        visited[i][j] = true;
        boolean top = search(board, word, i - 1, j, index + 1);
        boolean bottom = search(board, word, i + 1, j, index + 1);
        boolean left = search(board, word, i, j - 1, index + 1);
        boolean right = search(board, word, i, j + 1, index + 1);
        return top || bottom || left || right;
    }

    public List<List<Integer>> findSubsequences2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> path = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, path, res);
        return res;
    }
    public void dfs(int[] nums, int start, List<Integer> path, List<List<Integer>> res){
        if(path.size() >= 2){
            res.add(new ArrayList<>(path));
        }
        Set<Integer> set = new HashSet<>();
        for(int i = start; i < nums.length; i++){
            if(!path.isEmpty() && nums[i] < path.getLast()){
                continue;
            }
            if(set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            path.add(nums[i]);
            dfs(nums, i + 1, path, res);
            path.removeLast();

        }
    }

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, len = nums.length, res = 0;
        for(int i = 0; i < len; i++){
            sum += nums[i];
            int need = sum - k;
            if(map.containsKey(need)){
                res += map.get(need);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    public int findMaxLength(int[] nums) {
        int len = nums.length;
        for(int i = 0; i < len; i++){
            if(nums[i] == 0){
                nums[i] = -1;
            }
        }
        int sum = 0, maxLen = 0;;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for(int i = 0; i < len; i++){
            sum += nums[i];
            if(map.containsKey(sum)){
                int index = map.get(sum);
                maxLen = Math.max(maxLen, i - index);
            } else {
                map.put(sum, i);
            }
        }
        return maxLen;
    }



    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }
        // use a minHeap to choose the smallers pair
        Comparator<int[]> comparator = new Comparator<>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return (nums1[o1[0]] + nums2[o1[1]]) - (nums1[o2[0]] + nums2[o2[1]]); // 升序排序：小的排前面
            }
        };
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(comparator);
        // put k nums1 in the heap
        for(int i = 0; i < nums1.length; i++){
            minHeap.offer(new int[]{0, i});
        }
        while(!minHeap.isEmpty() && k > 0){
            int[] pair = minHeap.poll();
            int i = pair[0];
            int j = pair[1];
            List<Integer> item = new ArrayList<>();
            item.add(nums1[i]);
            item.add(nums2[j]);
            res.add(item);
            // 只扩展 nums2[j+1]，避免暴力遍历
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{i, j + 1});
            }
            k--;
        }
        return res;
    }


    public String decodeString(String s) {
        if(s.isEmpty()){
            return "";
        }
        Deque<Integer> numStack = new LinkedList<>();
        Deque<StringBuilder> strStack = new LinkedList<>();
        StringBuilder numStr = new StringBuilder();
        StringBuilder repeateStr = new StringBuilder();
        int number = 0;
        for(char c: s.toCharArray()){
            // if it is a number
            if(c >= '0' && c <= '9'){
                numStr.append(c);
                number = Integer.parseInt(numStr.toString());
            } else if(c == '['){
                // push number and current string into stacks
                numStack.push(number);
                strStack.push(repeateStr);
                // reset number and current string
                numStr = new StringBuilder();
                repeateStr = new StringBuilder();
            } else if(c == ']'){
                int count = numStack.pop();
                StringBuilder inner = strStack.pop();
                for(int i = 0; i < count; i++){
                    inner.append(repeateStr);
                }
                repeateStr = inner;
            } else {
                repeateStr.append(c);
            }
        }
        return repeateStr.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        int len = strs.length;
        int[] used = new int[len];
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            Map<Character, Integer> map = new HashMap<>();
            String str = strs[i];
            if (used[i] == 0) {
                for (char ch : str.toCharArray()) {
                    map.put(ch, map.getOrDefault(ch, 0) + 1);
                }
                List<String> item = new ArrayList<>();
                for (int j = 0; j < len; j++) {
                    if (used[j] == 0) {
                        String str2 = strs[j];
                        // 方法，判断str是否跟map的单词一样
                        Map<Character, Integer> temp = new HashMap<>(map);
                        if (isSame(str2, temp)) {
                            item.add(str2);
                            used[j] = 1;
                        }
                    }
                }
                if (!item.isEmpty()) {
                    res.add(item);
                }
            }
        }
        return res;
    }


    public boolean isSame(String str, Map<Character, Integer> map){
        for(char ch: str.toCharArray()){
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) - 1);
            } else {
                return false;
            }
        }
        // check map's value, if exists value != 0, return false
        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            if(entry.getValue() != 0){
                return false;
            }
        }
        return true;
    }







}
