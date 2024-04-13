package Algorithm.mytree;

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
        result.add(root.getData());
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
            result.add(root.getData());
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
                    res.add(temp.getData());
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
            resTree.push(root.getData());
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

    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int len = queue.size();     // used to record how many elements there are in a layer
            while (len > 0){
                TreeNode temp = queue.poll();
                level.add(temp.getData());
                if(temp.getLeft() != null) queue.add(temp.getLeft());
                if(temp.getRight() != null) queue.add(temp.getRight());
                len--;
            }
            res.add(level);
        }
        return res;
    }
}
