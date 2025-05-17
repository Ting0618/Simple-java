package algorithm241012.mystack;

public class Test {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        int[] nums = {1,1};
        int res = myStack.largestRectangleArea(nums);
        System.out.println(res);
    }
}
