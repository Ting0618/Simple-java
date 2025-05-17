package algorithm241012.mytree;

import algorithm241012.mytree.TreeNode;

import java.util.List;

/**
 * @author ting
 */
public class Test {


    public static void main(String[] args) {
//        TreeNode j = new TreeNode(5, null,null);
//        TreeNode i = new TreeNode(1,null,null);
//        TreeNode h = new TreeNode(4,j,i);
//        TreeNode g = new TreeNode(13,null,null);
//        TreeNode f = new TreeNode(8,g,h);
        TreeNode e = new TreeNode(7, null, null);
        TreeNode d = new TreeNode(15,null,null);
        TreeNode c = new TreeNode(20,d,e);
        TreeNode b = new TreeNode(9,null,null);
//        TreeNode b = new TreeNode(-1,null,null);
        TreeNode root = new TreeNode(3,b,c);
        MyTree myTree = new MyTree();
//        boolean res = myTree.hasPathSum(root, 22);
//        List<List<Integer>> path = myTree.pathSum(root,22);
//        int aa = myTree.diameterOfBinaryTree(root, 0,0);
//        StringBuilder sb = new StringBuilder();
        String res = myTree.serialize(root);
        System.out.println(res);
        TreeNode n = myTree.deserialize(res);
//        System.out.println(sb);
//        List<List<Integer>> res = myTree.zigzagLevelOrder(root);
//        for(List<Integer> item: res){
//            System.out.println(item);
//        }
    }
}
