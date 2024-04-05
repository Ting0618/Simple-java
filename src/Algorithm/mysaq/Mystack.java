package Algorithm.mysaq;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * create by: Ting
 * description: TODO operations with stack,implemented using queue
 * create time: 2024/4/4 16:17
 */
public class Mystack {
    Queue<Integer> queue;

    public Mystack() {
        queue = new LinkedList<>();
    }

    /**
     * create by: Ting
     * description: TODO Pushes element x to the top of the stack.
     * create time: 2024/4/5 15:14
     */
    public void push(int x) {
        queue.add(x);
    }

    /**
     * create by: Ting
     * description: TODO Removes the element on the top of the stack and returns it.
     * create time: 2024/4/5 15:14
     */
    public int pop(){
        reArrange();
        return queue.poll();
    }
    // reorder,put the previous elements to the back
    private void reArrange() {
        int i = queue.size() - 1;
        while (i > 0) {
            int temp = queue.remove();
            queue.add(temp);
            i--;
        }
    }

    /**
     * description: TODO Returns the element on the top of the stack.
     * create time: 2024/4/5 15:36
     */
    public int top(){
        reArrange();
        int res = queue.poll();
        queue.add(res);
        return res;
    }

    /**
     * create by: Ting
     * description: TODO Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
     * create time: 2024/4/5 16:57
     */
    public boolean isValid(String s){
        boolean res = false;
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for(char c:arr){
            // if it's a right parenthesis,put it into stack
            if(c == '{' || c == '(' || c == '[') stack.add(c);
            // if not,
            else {
                if(stack.empty()){  // when there is only left parenthesis, which means the stack is empty
                    return false;
                }
                char temp = stack.peek();
                if(c == '}'){
                    if(temp == '{') stack.pop();
                    else return false;
                }
                if(c == ')'){
                    if(temp == '(') stack.pop();
                    else return false;
                }
                if(c == ']'){
                    if(temp == '[') stack.pop();
                    else return false;
                }
            }
        }
        if(stack.empty()) res = true;
        return res;
    }

}
