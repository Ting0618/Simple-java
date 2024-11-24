package algorithm241012.mytree;

import java.util.List;

/**
 * @author ting
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    List<TreeNode> children;
    TreeNode next;

    public TreeNode(){}
    public TreeNode(int val){
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

}
