package algorithm.mytree;

public class TreeNode {
    private int data;
    private TreeNode left;
    private TreeNode right;
    public TreeNode(int data, TreeNode left, TreeNode rightChild){
        this.data = data;
        this.left = left;
        this.right = rightChild;
    }
//    public TreeNode(int data, TreeNode left){
//        this.data = data;
//        this.left = left;
//    }
    public TreeNode(int data){
        this.data = data;
    }
    public int getData(){
        return this.data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
