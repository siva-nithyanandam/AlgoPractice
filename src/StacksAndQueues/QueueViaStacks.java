package StacksAndQueues;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Implement a MyQueue class which implements a queue using two stacks.
 */

/**
 * Idea is, Add elements to stack as it is. While removing elements, push to second stack(So that,
 * bottom comes top) and finally pop from second stack.
 * Result:
 * 1
 * 2
 * 3
 */
public class QueueViaStacks {

  static class MyQueue {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public void enQueue(int data) {
      s1.push(data);
    }

    public int deQueue() {
      if (!s2.isEmpty()) {
        return s2.pop();
      } else if (!s1.isEmpty()) {
        while(!s1.isEmpty()) {
          s2.push(s1.pop());
        }
        return s2.pop();
      } else {
        throw new EmptyStackException();
      }
    }
  }

  public static void main(String[] args) {
    MyQueue myQueue = new MyQueue();
    myQueue.enQueue(1);
    myQueue.enQueue(2);
    myQueue.enQueue(3);

    System.out.println(myQueue.deQueue());
    System.out.println(myQueue.deQueue());
    System.out.println(myQueue.deQueue());
//    System.out.println(myQueue.deQueue());
  }

}
