package algorithm241012.mytree;

import java.util.List;
import java.util.Objects;

/**
 * @author ting
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public List<TreeNode> children;
    public TreeNode next;

    public TreeNode(){}
    public TreeNode(int val){
        this.val = val;
    }
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val && Objects.equals(left, treeNode.left) && Objects.equals(right, treeNode.right) && Objects.equals(children, treeNode.children) && Objects.equals(next, treeNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right, children, next);
    }
}
