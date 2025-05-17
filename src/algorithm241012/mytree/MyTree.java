package algorithm241012.mytree;

import java.util.*;

/**
 * @author ting
 */
public class MyTree {
    /**
     * description: TODO 51. Binary Tree (lc 144)Preorder Traverse --- iteration approach
     * create time: Nov 07 2024 11:25
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // get current node and recorde it's value
            TreeNode cur = stack.pop();
            res.add(cur.val);
            // put right child first, cause this is a stack, first in last out, the order in stack should be right->mid->left
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;
    }

    /**
     * description: TODO 52. Binary Tree (lc 144)inorder Traverse --- iteration approach
     * create time: Nov 07 2024 12:01
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        // store all elements into the stack, until find the most left child, store the val of the left node, then pop
        // stack, store this node and the traverse this node's right node
        while (root != null || !stack.isEmpty()) {
            // store node into stack until find the most left node
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                // when the code reaches at this here, indicate that cur is null
                // get the peek value of stack
                TreeNode pop = stack.pop();
                res.add(pop.val);
                // traverse the right side
                root = pop.right;
            }
        }
        return res;
    }

    /**
     * description: TODO 53. Binary Tree (lc 145)postorder Traverse --- iteration approach
     * create time: Nov 07 2024 12:20
     * Note: preorder: mid->left->right  postorder: left->right->mid
     * the order of pushing into the stack: preorder: mid->right->left, postorder:mid->left->right
     * reverse the final result we can get correct order
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // get the current node in the stack
            TreeNode cur = stack.pop();
            res.add(cur.val);
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        Collections.reverse(res);
        return res;
    }

    /**
     * description: TODO 54. Binary Tree (lc 102) level Order Traverse --- iteration by queue
     * create time: Nov 07 2024 12:56
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len;
        while (!queue.isEmpty()) {
            // record the number of nodes in the queue
            len = queue.size();
            // store the value of every level
            List<Integer> vals = new LinkedList<>();

            // Control the number of iterations for each level
            while (len > 0) {
                TreeNode temp = queue.poll();
                vals.add(temp.val);
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
                len--;
            }

            // add vals here, the result should be List<List<Integer>>
            res.add(vals);
        }
        return res;
    }

    /**
     * description: TODO 55. (lc 199) Binary Tree Right Side View
     * create time: Nov 08 2024 09:55
     * Note: to find the right most node of each level, the tail node of queue
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len;
        while (!queue.isEmpty()) {
            len = queue.size();
            while (len > 0) {
                // get the front element of queue
                TreeNode temp = queue.poll();
                // when this is the last element of queue, store the value
                if (len == 1) {
                    res.add(root.val);
                }
                // store it's left and right child
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
                len--;
            }
        }
        return res;
    }

    /**
     * description: TODO 56. (lc 637) Average of Levels in Binary Tree
     * create time: Nov 08 2024 10:20
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len, count;
        // the return value is double
        double sum;
        while (!queue.isEmpty()) {
            len = queue.size();
            // record the number of each level
            count = len;
            sum = 0;
            while (len > 0) {
                TreeNode temp = queue.poll();
                sum = sum + temp.val;
                if (temp.left != null) queue.offer(temp.left);
                if (temp.right != null) queue.offer(temp.right);
                len--;
            }
            res.add(sum / count);
        }
        return res;
    }

    /**
     * description: TODO 57. (lc 429) N-ary Tree Level Order Traverse
     * create time: Nov 08 2024 10:43
     */
    public List<List<Integer>> naryLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len;
        while (!queue.isEmpty()) {
            len = queue.size();
            List<Integer> vals = new LinkedList<>();
            while (len > 0) {
                TreeNode temp = queue.poll();
                vals.add(temp.val);
                // here, traverse its children
                List<TreeNode> children = temp.children;
                for (TreeNode node : children) {
                    // in a tree, you need to check if the node is not a null node
                    if (node != null) {
                        queue.offer(node);
                    }
                }
                len--;
            }
            res.add(vals);
        }
        return res;
    }

    /**
     * description: TODO 58. (lc 515) Find Largest in Each Tree Row
     * create time: Nov 08 2024 11:04
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len;
        while (!queue.isEmpty()) {
            // Pay special attention when setting the maximum and minimum values
            int max = Integer.MIN_VALUE;
            len = queue.size();
            while (len > 0) {
                TreeNode temp = queue.poll();
                max = Math.max(max, temp.val);
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
                len--;
            }
            res.add(max);
        }
        return res;
    }

    /**
     * description: TODO 59. (lc 116) Populating Next Right Pointers in Each Node
     * create time: Nov 08 2024 12:03
     */
    public TreeNode connect(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int len;
        queue.offer(root);
        while (!queue.isEmpty()) {
            len = queue.size();
            TreeNode preNode = new TreeNode();

            // Start iterating through the data at each level.
            for (int i = 0; i < len; i++) {
                TreeNode cur;
                // --------------------------
                if (i == 0) {
                    // 1. get the first node of each level
                    cur = queue.poll();
                    preNode = cur;
                } else {
                    cur = queue.poll();
                    preNode.next = cur;
                    preNode = preNode.next;
                }
                // ---------------------------
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            // the end point of each level

            preNode.next = null;
        }
        return root;
    }

    /**
     * description: TODO 60. (lc 104) Maximum Deep of Binary Tree --- level traverse
     * create time: Nov 09 2024 01:09
     */
    public int maxDepth(TreeNode root) {
        // use level traverse
        int deep = 0;
        if (root == null) {
            return deep;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len;
        while (!queue.isEmpty()) {
            len = queue.size();
            deep++;
            while (len > 0) {
                TreeNode temp = queue.poll();
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
                len--;
            }
        }
        return deep;
    }

    /**
     * description: TODO 60. (lc 104) Maximum Deep of Binary Tree
     * create time: Nov 09 2024 09:19
     */
    public int maxDepth2(TreeNode root, int deep) {
        if (root == null) {
            return deep;
        }
        deep++;
        int leftDeep = maxDepth2(root.left, deep);
        int rightDeep = maxDepth2(root.right, deep);
        return Math.max(leftDeep, rightDeep);
    }

    /**
     * description: TODO 60. (lc 104) Maximum Deep of Binary Tree --- postorder traverse
     * create time: Nov 09 2024 09:40
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDeep = maxDepth2(root.left);
        int rightDeep = maxDepth2(root.right);
        return Math.max(leftDeep, rightDeep) + 1;
    }

    /**
     * description: TODO 64. Maximum Deep of Nary-Tree
     * create time: Nov 09 2024 09:43
     */
    public int maxNtreeDepth(TreeNode root, int deep) {
        if (root == null) {
            return deep;
        }
        deep++;
        int max = 0;
        List<TreeNode> children = root.children;
        if (children != null) {
            for (TreeNode node : children) {
                int depth = maxNtreeDepth(node, deep);
                max = Math.max(max, depth);
            }
        }
        return max;
    }

    /**
     * description: TODO 64. Maximum Deep of Nary-Tree
     * create time: Nov 09 2024 10:23
     */
    public int maxNtreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int max = 0;
        List<TreeNode> children = root.children;
        if (children != null) {
            for (TreeNode child : children) {
                int childDeep = maxNtreeDepth(child);
                max = Math.max(max, childDeep);
            }
        }
        return max + 1;
    }

    /**
     * description: TODO 61. (lc 111) Minimum Deep of Binary Tree
     * create time: Nov 09 2024 01:18
     */
    public int minDepth(TreeNode root) {
        int minDeep = 0;
        if (root == null) {
            return minDeep;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int len;
        while (!queue.isEmpty()) {
            len = queue.size();
            minDeep++;
            while (len > 0) {
                TreeNode temp = queue.poll();
                if (temp.left == null && temp.right == null) {
                    return minDeep;
                }
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
                len--;
            }
        }
        return minDeep;
    }

    /**
     * description: TODO 62. (lc 226) Invert Binary Tree
     * create time: Nov 09 2024 01:30
     */
    public TreeNode invertTree(TreeNode root) {
        // ????
//        invert(root.left);
//        invert(root.right);
        invert(root);
        return root;
    }

    public void invert(TreeNode root) {
        if (root == null) {
            return;
        }
        invert(root.left);
        invert(root.right);
        // swap left child with right child
        TreeNode temp;
        temp = root.left;
        root.left = root.right;
        root.right = temp;
    }

    /**
     * description: TODO 63. (lc 101) Symmetric Tree
     * create time: Nov 09 2024 02:39
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return false;
        }
        // compare with left subtree and right subtree, not node
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        // when there is a null node between left and right
        if (left == null && right == null) {
            return true;
        } else if (left == null) {
            return false;
        } else if (right == null) {
            return false;
        } else {
            if (left.val != right.val) {
                return false;
            }
        }
        // left and right are not null and have the same value
        // compare inside nodes
        boolean inside = compare(left.right, right.left);
        boolean outside = compare(left.left, right.right);
        return inside && outside;
    }

    /**
     * description: TODO 65. (lc 222) Count Complete Tree Nodes
     * create time: Nov 09 2024 10:43
     */
    public int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftCount = countNodes(root.left);
        int rightCount = countNodes(root.right);
        return leftCount + rightCount + 1;
    }

    public int countNodes(TreeNode root, int count) {
        if (root == null) {
            return count;
        }
        count++;
        count = countNodes(root.left, count);
        count = countNodes(root.right, count);
        return count;
    }

    /**
     * description: TODO 66. (lc 110) Balanced Binary Tree
     * create time: Nov 09 2024 11:15
     */
    public boolean isBalanced(TreeNode root) {
        int hight = getHeight(root);
        return hight != -1;
    }

    int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        // this is the point
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeight(root.right);
        // and here
        if (rightHeight == -1) {
            return -1;
        }
        int dif = Math.abs(leftHeight - rightHeight);
        // and here
        return dif > 1 ? -1 : dif;
    }

    /**
     * description: TODO 67. (lc 257) Binary Tree Path
     * create time: Nov 09 2024 12:04
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        List<Integer> valList = new LinkedList<>();
        binaryTreePaths(root, res, valList);
        return res;
    }

    public void binaryTreePaths(TreeNode root, List<String> list, List<Integer> valList) {
        // add root node first, cause if there is only one node, it would return at the first if sentence
        valList.add(root.val);
        // deal with left node
        if (root.left == null && root.right == null) {
            StringBuilder str = new StringBuilder();
            int size = valList.size();
            for (int i = 0; i < size - 1; i++) {
                str.append(valList.get(i)).append("->");
            }
            // handle the last val
            str.append(valList.get(size - 1));
            // one path
            list.add(str.toString());
            return;
        }
        // in order to ensure the return condition root is not null, we need to ...
        if (root.left != null) {
            binaryTreePaths(root.left, list, valList);
            // remove the last node val
            valList.removeLast();
        }
        if (root.right != null) {
            binaryTreePaths(root.right, list, valList);
            valList.removeLast();
        }
    }

    /**
     * description: TODO 68. (lc 404)Sum of Left Leaves
     * create time: Nov 10 2024 09:17
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftValue = sumOfLeftLeaves(root.left);
        int rightValue = sumOfLeftLeaves(root.right);

        int midValue = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            midValue = root.left.val;
        }
        return midValue + leftValue + rightValue;
    }

    /**
     * description: TODO
     * create time: Nov 11 2024 13:03
     */
    public int sumOfLeftLeaves2(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        // check if this node is a leaf node
        if (root.left != null && root.left.left == null && root.left.right == null) {
            // if it is
            sum += root.left.val;
            // at this point, we can't return, if we return, it will stop recursion, it will only find one or two nodes
//            return sum;
        }

        if (root.left != null) {
            sum = sumOfLeftLeaves2(root.left, sum);
        }
        if (root.right != null) {
            sum = sumOfLeftLeaves2(root.right, sum);
        }
        return sum;
    }

    /**
     * description: TODO 69. lc 513 Find Bottom Left Tree Node
     * create time: Nov 10 2024 10:38
     */
    public int findBottomLeftValue(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = Integer.MAX_VALUE;
        int len;
        while (!queue.isEmpty()) {
            len = queue.size();
            TreeNode temp;
            for (int i = 0; i < len; i++) {
                temp = queue.poll();
                if (i == 0) {
                    res = temp.val;
                }
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
        }
        return res;
    }

    /**
     * description: TODO 70. lc 112, Path Sum --- postorder
     * create time: Nov 10 2024 11:13
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        // when it is a leave node
        if (root.left == null && root.right == null) {
            // when sum - root.val == 0, which means This is the path we need, with node values adding up to sum.
            return sum - root.val == 0;
        }
        if (root.left != null) {
            // why sum should be sum - root.val? why is not root.left.val?
            boolean left = hasPathSum(root.left, sum - root.val);
            // what does it mean when left == true?
            // means we find a path that is eligible, so it's time to return
            if (left) {
                return true;
            }
            // what if the left == false? which means the left node of root doesn't fit the condition,
            // we need to traverse the right node of root to find if there exists such path

        }
        if (root.right != null) {
            boolean right = hasPathSum(root.right, sum - root.val);
            if (right) {
                return true;
            }
        }
        // there we can get the parent note of current leaf node
        System.out.println(root.val);
        return false;
    }

    /**
     * create time: Nov 11 2024 10:15
     * Note: if we calculate the sum from leaf node to root? then compare with target sum
     * so we need inorder
     */
    public boolean pathSum3(TreeNode root, int count, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            count = count + root.val;
            return count == targetSum;
        }
        if (root.left != null) {
            boolean left = pathSum3(root.left, count, targetSum);
            if (left) return true;
        }
        if (root.right != null) {
            boolean right = pathSum3(root.right, count, targetSum);
            if (right) return true;
        }
        return false;
    }


    /**
     * description: TODO 71. lc Path Sum 2
     * create time: Nov 11 2024 11:54
     */
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        List<Integer> path = new LinkedList<>();
        findPath(root, targetSum, path, res);
        return res;
    }

    public void findPath(TreeNode root, int targetSum, List<Integer> list, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        // if it's leaf node, determine weather need to collect
        if (root.left == null && root.right == null) {
            // it's time to collect
            if (targetSum - root.val == 0) {
                // can't add list to res, because list will be modified during recursion, we need to use a new List
                List<Integer> temp = new LinkedList<>(list);
                res.add(temp);
            }
            // return to last level of recursion
            return;
        }

        if (root.left != null) {
            findPath(root.left, targetSum - root.val, list, res);
            list.removeLast();
        }

        if (root.right != null) {
            findPath(root.right, targetSum - root.val, list, res);
            list.removeLast();
        }
    }


    /**
     * description: TODO 72. lc543 Diameter of Binary Tree
     * create time: Nov 12 2024 19:25
     */
    int maxDeep = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        // get the maximum deep of left child and right child, then add them together
        if (root == null) {
            return 0;
        }
        int leftDeep = diameterOfBinaryTree(root.left);
        int rightDeep = diameterOfBinaryTree(root.right);
        maxDeep = Math.max(maxDeep, leftDeep + rightDeep + 1);
        return leftDeep + rightDeep + 1;
    }

    /**
     * description: TODO 73. 1、如果把根节点看做第 1 层，如何打印出每一个节点所在的层数？2、如何打印出每个节点的左右子树各有多少节点？
     * create time: Nov 13 2024 12:08
     */
    int deep = 0;

    public int printInfo(TreeNode root) {
        if (root == null) {
            // return to last root
            return 0;
        }
        deep++;
        System.out.println("root" + root.val + "'s deep: " + deep);
        int leftCount = printInfo(root.left);
        int rightCount = printInfo(root.right);
        deep--;
        System.out.println("root" + root.val + "'s leftCount: " + leftCount);
        System.out.println("root" + root.val + "'s rightCount: " + rightCount);
        return leftCount + rightCount + 1;
    }

    /**
     * description: TODO 74. lc700 Search in a BST
     * create time: Nov 14 2024 10:58
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val == root.val) {
            return root;
        }
        if (val > root.val) {
            TreeNode right = searchBST(root.right, val);
            if (right != null) {
                return right;
            }
        } else {
            return searchBST(root.left, val);
        }
        return null;
    }

    /**
     * description: TODO 75. lc230 Kth smallest element in a BST
     * create time: Nov 14 2024 11:23
     */
    int count = 0;
    int val = Integer.MAX_VALUE;

    public void kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        kthSmallest(root.left, k);
        count++;
        if (count == k) {
            val = root.val;
            return;
        }
        kthSmallest(root.right, k);
    }

    /**
     * description: TODO 76. lc538 Convert BST to a Greater Tree
     * create time: Nov 14 2024 12:01
     */
    public TreeNode convertBST(TreeNode root, int sum) {
        if (root == null) {
            return null;
        }
        // the key is from right to left, inorder print is decreasing, then we can calculate the sum
        convertBST(root.right, sum);
        // inorder point
        sum += root.val;
        root.val = sum;
        // then left child
        convertBST(root.left, sum);
        return root;
    }

    /**
     * description: TODO 77. lc98 Validate Binary Search Tree
     * create time: Nov 14 2024 12:17
     */
    public boolean isValidBST(TreeNode root) {
        // if a BST is valid, its inorder should be ascending
        List<Integer> res = new LinkedList<>();
        inorderBST(root, res);
        for (int i = 0; i < res.size(); i++) {
            if (i > 0) {
                if (res.get(i) <= res.get(i - 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void inorderBST(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorderBST(root.left, res);
        res.add(root.val);
        inorderBST(root.right, res);
    }

    /**
     * description: TODO 78. lc654 Construct Maximum Binary Tree
     * create time: Nov 15 2024 10:22
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildTree(nums, 0, nums.length);
    }

    public TreeNode buildTree(int[] nums, int start, int end) {
        int len = nums.length;
        if (len == 0) {
            return null;
        }
        if (len == 1) {
            return new TreeNode(nums[start]);
        }

        // find the maximum value of nums from start to end
        int maxIndex = start;
        for (int i = start; i < end + 1; i++) {
            if (nums[maxIndex] < nums[i]) {
                maxIndex = i;
            }
        }
        TreeNode node = new TreeNode(nums[maxIndex]);
        TreeNode left = buildTree(nums, start, maxIndex);
        TreeNode right = buildTree(nums, maxIndex + 1, end);
        node.left = left;
        node.right = right;
        return node;
    }

    /**
     * description: TODO 79. lc105 Construct Binary Tree From Preorder and Inorder Traverse
     * create time: Nov 15 2024 11:29
     */
    Map<Integer, Integer> inMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    public TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preEnd - preStart < 1) {
            return null;
        }
        if (preEnd - preStart == 1) {
            return new TreeNode(preorder[preStart]);
        }
        // the first node of preorder is the root node of whole tree
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        // find the index in inorder while value is preorder[preStart
        // we can use a for loop to find the index of rootVal in inorder, while use map is more efficient
        int index = inMap.get(rootVal);
        // we can know that from inStart to index - 1 belongs to left subtree, from index + 1 to inEnd belongs to right subtree, the value of index is the root
        int leftSize = index - inStart;
        root.left = build(preorder, preStart + 1, preStart + leftSize + 1, inorder, inStart, index);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);
        return root;
    }

    /**
     * description: TODO 80. lc106 Construct Binary Tree from Inorder and Postorder Traverse
     * create time: Nov 15 2024 12:16
     */
    Map<Integer, Integer> inMap2 = new HashMap<>();

    public TreeNode buildTreeByInAndPost(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            inMap2.put(inorder[i], i);
        }
        return consByInAndPost(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    public TreeNode consByInAndPost(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        // 判断是否为无效区间
        if (inStart >= inEnd || postStart >= postEnd) {
            return null;
        }
        // the last value of postorder is the root node value
        int rootval = postorder[postEnd - 1];
        TreeNode root = new TreeNode(rootval);
        int index = inMap2.get(rootval);
        // range: [ )
        // inorder:[inStar, index), [index + 1, inEnd)
        // postorder: [postStart, postStart + leftSize), [postStart + leftSize, postEnd - 1)
        int leftSize = index - inStart;
        root.left = consByInAndPost(inorder, inStart, index, postorder, postStart, postStart + leftSize);
        root.right = consByInAndPost(inorder, index + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);
        return root;
    }

    public static int solution(int[] points, String tokens) {
        System.err.println("Tip: Use System.err.println() to write debug messages on the output tab.");
        int len = points.length;
        char[] ch = tokens.toCharArray();
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (ch[i] == 'T') {
                res = res + points[i];
            }
            if (i > 0 && ch[i] == ch[i - 1] && ch[i] == 'T') {
                res++;
            }
        }
        return res;
    }

    /**
     * description: TODO 81. lc701 Insert into a Binary Search Tree
     * create time: Nov 16 2024 08:43
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (val < root.val) {
            TreeNode left = insertIntoBST(root.left, val);
            // actually, left would never be null
            if (left != null) {
                root.left = left;
            }
        } else {
            TreeNode right = insertIntoBST(root.right, val);
            if (right != null) {
                root.right = right;
            }
        }
        return root;
    }

    /**
     * description: TODO 82. lc450 Delete a Node from a BST
     * create time: Nov 16 2024 08:56
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        // if we find the val
        if (root.val == key) {
            // the first case: this node is a leaf node
            if (root.left == null && root.right == null) {
                // make this node null, root == null
                return null;
            }
            // the second case: this node has only one child, let its child replace it
            if (root.left == null) {
                return root.right;
            }
            // the third case: only has left child
            if (root.right == null) {
                return root.left;
            }
            // the third case: the node has both left and right child
            // replace the root node with the maximum value of left subtree or the minimum value of right substree
            // find the minimum of the right subtree
            TreeNode cur = root.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            // make the value of root equals min
            root.val = cur.val;
            // delete the min value of the tree
            root.right = deleteNode(root.right, cur.val);
            // this root can be omitted, because next step is the final return at the bottom
            return root;
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }


    /**
     * description: TODO 83. lc108 Convert Sorted Array to a Height-balanced Binary Tree
     * create time: Nov 16 2024 11:37
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start >= end) {
            return null;
        }
        if (end - start == 1) {
            return new TreeNode(nums[start]);
        }
        int mid = start + ((end - start) / 2);
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, start, mid);
        root.right = sortedArrayToBST(nums, mid + 1, end);
        return root;
    }

    /**
     * description: TODO 84. lc652 Find Duplicate Subtrees
     * create time: Nov 17 2024 09:03
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        HashMap<String, Integer> map = new HashMap<>(16);
        findDuplicate(root, map, res);
        return res;
    }

    public String findDuplicate(TreeNode root, HashMap<String, Integer> map, List<TreeNode> res) {
        if (root == null) {
            return "#";
        }
        String left = findDuplicate(root.left, map, res);
        String right = findDuplicate(root.right, map, res);
        // record a subtree, by string
        String str = left + "," + right + "," + root.val;
        // add each subtree into a map, record how many times the same subtree appears, the first time it would be 1
        map.put(str, map.getOrDefault(str, 0) + 1);

        // if the tree (root tree) is contained in the map
        if (map.containsKey(str) && map.get(str) == 2) {
            res.add(root);
        }
        return str;
    }

    public String serilise(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return "#";
        }
        String left = serilise(root.left, sb);
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }

        String right = serilise(root.right, sb);
        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(left).append(",").append(right).append(",").append(root.val);
        return left + "," + right + "," + root.val;
    }

    /**
     * description: TODO 85. lc530 Minimum Absolute Difference in BST
     * create time: Nov 17 2024 09:55
     */
    int minimumDifference = Integer.MAX_VALUE;
    TreeNode pre = null;

    public int getMinimumDifference(TreeNode root) {
        // the inorder traverse of a BST is sorted, during the traverse process we can calculate the minimum
        getMinimumDifference2(root);
        return minimumDifference;
    }

    public void getMinimumDifference2(TreeNode root) {
        if (root == null) {
            return;
        }
        // left
        getMinimumDifference2(root.left);
        // mid
        if (pre != null) {
            minimumDifference = Math.min(minimumDifference, root.val - pre.val);
        }
        pre = root;
        // right
        getMinimumDifference2(root.right);
    }

    /**
     * description: TODO 86. lc501 Find Mode in BST
     * create time: Nov 17 2024 10:29
     */
    TreeNode previous = null;
    int countFind = 0;
    int maxFind = Integer.MIN_VALUE;
    ArrayList<Integer> resFindList = new ArrayList<>();
    ;

    public int[] findMode(TreeNode root) {
        find(root);
        int[] res = new int[resFindList.size()];
        for (int i = 0; i < resFindList.size(); i++) {
            res[i] = resFindList.get(i);
        }
        return res;
    }

    public void find(TreeNode root) {
        if (root == null) {
            return;
        }
        find(root.left);
        if (previous == null) {
            countFind = 1;
        } else if (previous.val == root.val) {
            countFind++;
        } else {
            countFind = 1;
        }
        if (countFind > maxFind) {
            maxFind = countFind;
            resFindList.clear();
            resFindList.add(root.val);
        } else if (countFind == maxFind) {
            resFindList.add(root.val);
        }
        previous = root;
        find(root.right);
    }

    /**
     * description: TODO 87. lc236 Lowest Common Ancestor Of a Binary Tree
     * create time: Nov 17 2024 11:26
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    /**
     * description: TODO find node that value equals val
     * create time: Nov 18 2024 10:54
     */
    public TreeNode finVal(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        // traverse left subtree to find the val
        TreeNode left = finVal(root.left, val);
        // if we find in the left subtree
        if (left != null) {
            return left;
        }
        TreeNode right = finVal(root.right, val);
        // if we find in the right subtree
        if (right != null) {
            return right;
        }
        // if we didn't find
        return null;
    }

    /**
     * description: TODO we change method finVal() to as follows, what's the difference
     */
    public TreeNode finVal2(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        // traverse left subtree to find the val
        TreeNode left = finVal(root.left, val);
        TreeNode right = finVal(root.right, val);

        // postorder
        if (root.val == val) {
            return root;
        }
        // If the root is not the target node, check which side’s subtree contains it.
        return left != null ? left : right;
    }

    /**
     * description: TODO 88 lc1676 而是给你输入一个包含若干节点的列表 nodes（这些节点都存在于二叉树中），让你算这些节点的最近公共祖先。
     * create time: Nov 18 2024 11:24
     */
    TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        Map<Integer, Integer> map = new HashMap<>(16);
        for (TreeNode node : nodes) {
            map.put(node.val, map.getOrDefault(node.val, 0) + 1);
        }
        return findlowestCommonAncestor(root, map);
    }

    public TreeNode findlowestCommonAncestor(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) {
            return null;
        }
        if (map.containsKey(root.val)) {
            return root;
        }
        TreeNode left = findlowestCommonAncestor(root.left, map);
        TreeNode right = findlowestCommonAncestor(root.right, map);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    /**
     * description: TODO 89. 1644给你输入一棵不含重复值的二叉树的，以及两个节点 p 和 q，如果 p 或 q 不存在于树中，则返回空指针，否则的话返回 p 和 q 的最近公共祖先节点。
     * create time: Nov 18 2024 12:07
     */
    boolean findP = false;
    boolean findQ = false;

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode res = lowestCommonAncestor(root, p, q);
        if (!findP || !findQ) {
            return null;
        }
        // p 和 q 都存在二叉树中，才有公共祖先
        return res;
    }
    public TreeNode findPq(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        TreeNode left = findPq(root.left, p, q);
        TreeNode right = findPq(root.right, p, q);
        // node: root
        if (root.val == p.val) {
            findP = true;
            return root;
        } else if (root.val == q.val) {
            findQ = true;
            return root;
        }
        if(left != null && right != null){
            return root;
        }
        return left != null? left: right;
    }

    /**
     * description: TODO 90. lc235 the Lowest Common Ancestor of a BST
     * create time: Nov 18 2024 12:31
     */
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q){
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        return lowestCommonAncestorBST2(root, min, max);
    }
    public TreeNode lowestCommonAncestorBST2(TreeNode root, int p, int q) {
        if(root == null){
            return null;
        }
        if(root.val == p || root.val == q){
            return root;
        }
        TreeNode left = null;
        TreeNode right = null;
        if(root.val > q){
            left = lowestCommonAncestorBST2(root.left, p, q);
        }
        if(root.val < p){
            right = lowestCommonAncestorBST2(root.right, p, q);
        }
        if(root.val > p && root.val < q){
            return root;
        }
        return left != null? left: right;
    }

    /**
     * @Description TODO lc314 Vertical Order traverse a binary tree
     * @Date 2025/1/21 08:44
     **/
    public List<List<Integer>> verticalOrder(TreeNode root){
        // traverse tree by bfs
        if(root == null){
            return null;
        }
        List<List<Integer>> res = new LinkedList<>();
        Queue<Pair> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        Pair pair = new Pair(root, 0);
        queue.offer(pair);
        int min = 0, max = 0;
        while (!queue.isEmpty()){
            Pair cur = queue.poll();
            TreeNode curNode = cur.treeNode;
            int curColumn = cur.column;
            // map
            if(map.containsKey(curColumn)){
                map.get(curColumn).add(curNode.val);
            } else {
                map.put(curColumn, new ArrayList<>());
            }
            if(curNode.left != null){
                queue.offer(new Pair(curNode.left, curColumn - 1));
                min = Math.min(curColumn - 1, min);
            }
            if(curNode.right != null){
                queue.offer(new Pair(curNode.right, curColumn + 1));
                max = Math.max(curColumn + 1, max);
            }
        }
        // get all data from map
        for(int i = min; i <= max; i++){
            List<Integer> item = map.get(i);
            res.add(item);
        }
        return res;
    }

    public void preorder(TreeNode root){
        if(root == null){
            return;
        }
        System.out.println(root.val);
        preorder(root.left);
        preorder(root.right);
    }
    int summ = 0;
    public void preorderSum(TreeNode root){
        if(root == null){
            return;
        }
        System.out.print(root.val + "  ");
        summ += root.val;
        System.out.println(summ);
        preorderSum(root.left);
        preorderSum(root.right);
        summ -= root.val;
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int size = 0, depth = 0;
        while(!queue.isEmpty()){
            depth++;
            size = queue.size();
            List<Integer> item = new ArrayList<>();
            for(int i = 0; i < size; i++){
                TreeNode first = queue.poll();
                assert first != null;
                item.add(first.val);
                if(first.left != null){
                    queue.offer(first.left);
                }
                if(first.right != null){
                    queue.offer(first.right);
                }
                // if layer number is even, from right to left, reverse item
                if(i == size - 1 && depth % 2 == 0){
                    Collections.reverse(item);
                }
            }
            res.add(item);
        }
        return res;
    }

    public void count(TreeNode root, int count){
        if(root == null){
            return;
        }
        System.out.println(root.val + "  " + count);
        count(root.left, count + 1);
        count(root.right, count + 1);
    }

    int count2 = 0;
    public void count(TreeNode root){
        if(root == null){
            return;
        }
        count2++;
        System.out.println(root.val + "  " + count2);
        count(root.left);
        count(root.right);
    }

    public void preorder4(TreeNode root, int index) {
        if (root == null) {
            return;
        }
        System.out.println("Node " + index + ": Value = " + root.val);
        preorder4(root.left, index + 1);
        preorder4(root.right, index + 1);
    }

    String SEP = ",";
    String NULL = "#";
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }
    void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        serialize(root.left, sb);
        serialize(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            nodes.addLast(s);
        }
        return deserializeHelper(nodes);
    }

    TreeNode deserializeHelper(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String first = nodes.removeFirst();
        if (first.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left = deserializeHelper(nodes);
        root.right = deserializeHelper(nodes);
        return root;
    }

}
