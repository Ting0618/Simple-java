package Algorithm.mytree;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        TreeNode h = new TreeNode(7);
        TreeNode g = new TreeNode(6);
        TreeNode f = new TreeNode(5);
        TreeNode e = new TreeNode(4);
        TreeNode d = new TreeNode(3,g,h);
        TreeNode c = new TreeNode(2,e,f);
        TreeNode b = new TreeNode(1,d,null);
        TreeNode a = new TreeNode(-1,b,c);


        TreeNode n5 = new TreeNode(5);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(2,null,n4);
        TreeNode n2 = new TreeNode(2,n4,null);
        TreeNode n1 = new TreeNode(1,n2,n3);

        MyTree myTree = new MyTree();
        List<Integer> result = new ArrayList<>();
//        result = myTree.preOrder(a);
//        System.out.println(result);
//        result = myTree.pre(a);
//        System.out.println(result);
        TreeNode n = myTree.invertTree(a);
//        myTree.levelOrder(n);
//        System.out.println(result);
        myTree.levelOrder(n1);
        boolean bb = myTree.isSymmetric(n1);
        System.out.println(bb);
    }

}
