package algorithm241012.coding;

import algorithm241012.mytree.MyTree;
import algorithm241012.mytree.TreeNode;

import java.util.Arrays;
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
        int[][] grid = {
                {1, 1, 1, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };
        int[][] grid2 = {
                {0,1},
                {0,2},
                {0,3},
                {1,4},
        };
        int[] nums = {4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3};
        String[] words = {"hello","leetcode"};
        String order = "hlabcdefgijkmnopqrstuvwxyz";
//        List<String> res = whatever.findString("awedwfuri", 4);
        int res = whatever.longestConsecutive(nums);
        System.out.println(res);
    }
}
