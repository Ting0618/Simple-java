package algorithm.mytree;

import java.util.*;

/**
 * create by: Ting
 * description: TODO
 * create time: 2024/4/9 16:41
 */
public class MyTree {
    public MyTree() {
    }

    /**
     * create by: Ting
     * description: TODO preorder traversal of Tree (mid - left - right)
     * create time: 2024/4/9 21:49
     */
    public List<Integer> preOrder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        preorderTraversal(root, res);
        return res;
    }

    public void preorderTraversal(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(Integer.valueOf(root.getData()));
        preorderTraversal(root.getLeft(), result);
        preorderTraversal(root.getRight(), result);
    }

    /**
     * create by: Ting
     * description: TODO inorder traversal of Tree (left - mid - right)
     * create time: 2024/4/9 22:05
     */
    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.getLeft());
        System.out.print(root.getData() + ",");
        inorder(root.getRight());
    }

    public void postorder(TreeNode root) {
        if (root == null) return;
        postorder(root.getLeft());
        postorder(root.getRight());
        System.out.print(root.getData() + ",");
    }


    /**
     * create by: Ting
     * description: TODO iteration method of pre-order traversal
     * create time: 2024/4/10 21:49
     */
    public List<Integer> pre(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        stack.push(root);       // put the root node into stack first
        while (!stack.empty()) {
            root = stack.pop();     // remove the top element from the stack
            result.add(Integer.valueOf(root.getData()));
            if (root.getRight() != null) {
                stack.push(root.getRight());
            }
            if (root.getLeft() != null) {
                stack.push(root.getLeft());
            }
        }
        return result;
    }

    /**
     * create by: Ting
     * description: TODO iteration method of in-order traversal
     * create time: 2024/4/12 15:06
     */
    public List<Integer> inor(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null) {
            while (!stack.empty() || root != null) {
                if (root != null) {
                    stack.push(root);
                    root = root.getLeft();
                } else {
                    TreeNode temp = stack.pop();
                    res.add(Integer.valueOf(temp.getData()));
                    root = temp.getRight();
                }
            }
        }
        return res;
    }


    /**
     * create by: Ting
     * description: TODO 迭代的后序遍历方式
     * create time: 2024/4/13 13:26
     */
    public List<Integer> post(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> resTree = new Stack<>();
        if (root == null) return res;
        stack.push(root);
        while (!stack.empty()) {
            root = stack.pop();
            resTree.push(Integer.valueOf(root.getData()));
            if (root.getLeft() != null) {
                stack.push(root.getLeft());
            }
            if (root.getRight() != null) {
                stack.push(root.getRight());
            }
        }
        while (!resTree.empty()) {
            res.add(resTree.pop());
        }
        return res;
    }

    /**
     * create by: Ting
     * description: TODO 层序遍历
     * create time: 2024/4/13 14:33
     */

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int len = queue.size();     // used to record how many elements there are in a layer
            while (len > 0) {
                TreeNode temp = queue.poll();
                level.add(Integer.valueOf(temp.getData()));
                if (temp.getLeft() != null) queue.add(temp.getLeft());
                if (temp.getRight() != null) queue.add(temp.getRight());
                len--;
            }
            res.add(level);
        }
        System.out.println(res);
        return res;
    }

    /**
     * create by: Ting
     * description: TODO Given the root of a binary tree, invert the tree, and return its root.
     * create time: 2024/4/13 15:14
     */
    public TreeNode invertTree(TreeNode root) {
        if (root != null) {
            invertT(root);
        }
        return root;
    }

    private void invertT(TreeNode root) {
        if (root == null) return;
        if (root.getRight() == null && root.getLeft() == null) return;
        TreeNode temp = root.getLeft();
        root.setLeft(root.getRight());
        root.setRight(temp);
        invertT(root.getLeft());
        invertT(root.getRight());
    }

    /**
     * create by: Ting
     * description: TODO Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     * create time: 2024/4/13 16:25
     */
    public boolean isSymmetric(TreeNode root) {
        return isSubTree(root.getLeft(), root.getRight());
    }

    private boolean isSubTree(TreeNode left, TreeNode right) {
        if (left == null && right != null) {
            return false;
        } else if (left != null && right == null) {
            return false;
        } else if (left == null && right == null) return true;
        else if (left.getData() != right.getData()) {
            return false;
        }
        // 比较外侧
        boolean compareOutside = isSubTree(left.getLeft(), right.getRight());
        // 比较内侧
        boolean compareInside = isSubTree(left.getRight(), right.getLeft());
        return compareOutside && compareInside;
    }

    /**
     * create by: Ting
     * description: TODO Given the root of a binary tree, return its maximum depth
     * create time: 2024/4/14 16:07
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepth(root.getLeft());
        int rightDepth = maxDepth(root.getRight());
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * create by: Ting
     * description: TODO Given the root of a binary tree, return its minimum depth
     * create time: 2024/4/14 17:09
     */
    public int minDepth(TreeNode root) {
        int depth;
        if (root == null) return 0;
        int leftDepth = minDepth(root.getLeft());
        int rightDepth = minDepth(root.getRight());
        if (root.getLeft() == null) {     // 如果左子树为空，右子树不为空，说明最小深度是 1 + 右子树的深度。
            depth = rightDepth + 1;
        } else if (root.getRight() == null) {   // 右子树为空，左子树不为空，最小深度是 1 + 左子树的深度
            depth = leftDepth + 1;
        } else {
            depth = Math.min(leftDepth, rightDepth);     // 如果左右子树都不为空，返回左右子树深度最小值 + 1 。
        }
        return depth;
    }

    /**
     * create by: Ting
     * description: TODO Given the root of a complete binary tree, return the number of the nodes in the tree.
     * create time: 2024/4/14 18:01
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int leftTree = countNodes(root.getLeft());  // 得到左子树的节点个数
        int rightTree = countNodes(root.getRight());    //得到右节点的节点个数
        return leftTree + rightTree + 1;    // 返回 左节点 + 右节点 + 当前节点 = 当前树的节点
    }

    // 针对完全二叉树的解法
    public int countNodes2(TreeNode root) {
        int nodeSum = 0;
        if (root == null) return 0;
        TreeNode left = root.getLeft();
        TreeNode right = root.getRight();
        int leftNodes = 0;
        int rightNodes = 0;
        // 判断树是否是满二叉树
        while (left != null) {
            leftNodes++;
            left = left.getLeft();
        }
        while (right != null) {
            rightNodes++;
            right = right.getRight();
        }
        if (leftNodes == rightNodes) {
            nodeSum = 2 << leftNodes - 1;
        } else {
            nodeSum = countNodes2(root.getLeft()) + countNodes2(root.getRight()) + 1;
        }
        return nodeSum;
    }

    /**
     * create by: Ting
     * description: TODO Given a binary tree, determine if it is height-balanced
     * create time: 2024/4/15 15:41
     */
    public boolean isBalanced(TreeNode root) {
        int res = depth(root);
        if (res == -1) return false;
        return true;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.getLeft());
        if (left == -1) return -1;
        int right = depth(root.getRight());
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    /**
     * create by: Ting
     * description: TODO
     * create time: 2024/4/16 14:11
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();


        return res;
    }

    private StringBuilder traveresal(TreeNode root, StringBuilder res) {
        if (root.getLeft() == null && root.getRight() == null) {
            res.append(root.getData()).append(",");
            return res;
        }
        if (root.getLeft() != null) {
            res = traveresal(root.getLeft(), res);
        }
        if (root.getRight() != null) {
            res = traveresal(root.getRight(), res);
        }
        return res;
    }

    /**
     * create by: Ting
     * description: TODO find bottom left value
     * create time: 2024/4/20 14:31
     */
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int res = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int len = queue.size();
            int i = len;
            while (len > 0) {
                TreeNode temp = queue.poll();
                if(len == i){
                    res = temp.getData();
                }
                if (temp.getLeft() != null) {
                    queue.offer(temp.getLeft());
                }
                if (temp.getRight() != null) {
                    queue.offer(temp.getRight());
                }
                len--;
            }

        }
        return res;
    }

    /**
     * create by: Ting
     * description: TODO Given the root of a binary tree and an integer targetSum, return true if the tree
     * has a root-to-leaf path such that adding up all the values along the path equals targetSum.
     * create time: 2024/4/20 20:06
     */
    public boolean hasPathSum(TreeNode root,int sum){

        return false;
    }
    private int nodeSum(TreeNode root){
        if(root == null) return 0;
        int sum = root.getData();
        int left = nodeSum(root.getLeft());
        int right = nodeSum(root.getRight());

        return 0;
    }
}
