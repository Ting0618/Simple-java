package algorithm241012.mystack;

import java.util.Deque;
import java.util.LinkedList;

public class MyStack {
    /**
     * description: TODO 48. (lc 20) Valid Parentheses
     * create time: Nov 06 2024 10:13
     */
    public boolean isValid(String s){
        char[] ch = s.toCharArray();
        // use a stack match the right parentheses
        Deque<Character> stack = new LinkedList<>();
        for(char c: ch){
            // if it is left bracket, directly add into stack
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
                continue;
            }
            if(!stack.isEmpty()){
                char temp = stack.peek();
                if(c == ')' && temp == '('){
                    stack.pop();
                } else if (c == ']' && temp == '[') {
                    stack.pop();
                } else if (c == '}' && temp == '{') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    /**
     * description: TODO 49. (lc 1047) Remove All Adjacent Duplicate in String
     * create time: Nov 06 2024 10:53
     */
    public String removeDuplicates(String s){
        Deque<Character> stack = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        char[] ch = s.toCharArray();
        for(char c: ch){
            // stack is not empty and the peek element equal to c, pop it
            if(!stack.isEmpty() && stack.peek() == c){
                stack.pop();
            } else {
                // if it's null or not the same with the first element in the stack
                stack.push(c);
            }
        }
        // the element that remains in the stack is unique elements
        while (!stack.isEmpty()){
            res.insert(0, stack.pop());
        }
        return res.toString();
    }

    /**
     * description: TODO 50 (lc 150) Evaluate Reverse Polish Notation.
     * create time: Nov 06 2024 11:19
     * the point is you have to know how to calculate
     */
    public int evalRPN(String[] tokens){
        // If a number appears, push it onto the stack; if an operator appears,
        // pop the top two numbers, compute the result, and push it back onto the stack.
        Deque<Integer> stack = new LinkedList<>();
        for(String s: tokens){
            // if s is a number, push it into stack
            if(s.matches("^-?\\d+$")){
                stack.push(Integer.parseInt(s));
            } else {
                int temp1 = stack.pop();
                int temp2 = stack.pop();
                // temp1 is always behind an operator
                int temp3 = switch (s){
                    case "+" -> temp2 + temp1;
                    case "-" -> temp2 - temp1;
                    case "*" -> temp2 * temp1;
                    case "/" -> temp2 / temp1;
                    default -> 0;
                };
                stack.push(temp3);
            }
        }
        return stack.pop();
    }
}
