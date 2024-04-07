package Algorithm.mysaq;

public class Test {
    public static void main(String[] args) {
        Mystack mystack = new Mystack();
        String[] s = new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        int res = mystack.evalRPN(s);
        System.out.println(res);
    }
}
