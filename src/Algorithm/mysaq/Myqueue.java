package Algorithm.mysaq;

import java.util.Stack;

/**
 * create by: Ting
 * description: TODO queue implemented using stack
 * create time: 2024/4/5 14:34
 */
public class Myqueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;
    public Myqueue(){}
    /**
     * create by: Ting
     * description: TODO Pushes element x to the back of the queue
     * create time: 2024/4/4 16:21
     */
    public void push(int x){
        stackIn.push(x);
    }

    /**
     * create by: Ting
     * description: TODO Removes the element from the front of the queue and returns it.
     * create time: 2024/4/5 13:20
     */
    public int pop(){
        // If stackIn is empty, it means that there are either no elements or the elements are all in stackOut
        reverseStack();
        return stackOut.pop();
    }


    /**
     * create by: Ting
     * description: TODO Returns the element at the front of the queue.
     * create time: 2024/4/5 13:45
     */
    public int peek(){
        reverseStack();
        return stackOut.peek();
    }


    /**
     * create by: Ting
     * description: TODO Returns true if the queue is empty, false otherwise.
     * create time: 2024/4/5 13:55
     */
    public boolean empty(){
        return stackIn.empty() && stackOut.empty();
    }

    // put all elements into stackOut
    private void reverseStack(){
        if(!stackIn.empty()){
            // put all the elements from stackIn to stackOut
            while (!stackIn.empty()){
                stackOut.push(stackIn.pop());
            }
        }
    }



}
