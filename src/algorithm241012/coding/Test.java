package algorithm241012.coding;

import algorithm241012.mytree.MyTree;
import algorithm241012.mytree.TreeNode;

import java.util.List;

/**
 * @author ting
 */
public class Test {
    public static void main(String[] args) {
        TreeNode e = new TreeNode(7, null, null);
        TreeNode d = new TreeNode(15, null, null);
        TreeNode c = new TreeNode(20, d, e);
        TreeNode b = new TreeNode(9, null, null);
        TreeNode root = new TreeNode(3, b, c);
        Whatever whatever = new Whatever();
        int[] nums = {1,1,1,1};
//        List<String> res = whatever.findString("awedwfuri", 4);
        int res = whatever.removeDuplicates2(nums);
        System.out.println(res);
    }
}
