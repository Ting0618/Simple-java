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
        MyTree myTree = new MyTree();
        List<Integer> result = new ArrayList<>();
//        result = myTree.preOrder(a);
//        System.out.println(result);
//        result = myTree.pre(a);
//        System.out.println(result);
        myTree.levelOrder(a);
//        result = myTree.post(a);
//        System.out.println(result);

    }

}
