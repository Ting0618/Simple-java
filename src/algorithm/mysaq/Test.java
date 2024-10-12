package algorithm.mysaq;

public class Test {
    public static void main(String[] args) {
        Mystack mystack = new Mystack();
        String[] s = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
//        int res = mystack.evalRPN(s);
        int[] nums = {4,1,-1,2,-1,2,3};
        int[] res = mystack.topKfrequent(nums,2);
        for(int i: res){
            System.out.print(i+ " ");
        }

    }
}
