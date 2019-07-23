package StacksAndQueues;

import java.util.EmptyStackException;

/**
 * How would you design a stack which, in addition to push and pop, has a function min
 * which returns the minimum element? Push, pop and min should all operate in O(1) time.
 */

/**
 * Idea is to keep storing min element at Node itself until that Node level.
 * For example,
 * push(10) -> We store 10 as a data, and 10 as min
 * push(9) -> We store 9 as a data, and 9 as min
 * push(11) -> We store 11 as a data, and min remains 9 here.
 *
 * Results for below program:
 * Minimum here -> 10
 * Minimum here -> 9
 * Minimum here -> 9
 * Minimum here -> 7
 * Minimum here -> 6
 * Minimum here -> 1
 */
public class StackMin {

    private static class Node {
        private int data;
        private int minAtHere;
        private Node next;

        private Node(int data, int min) {
            this.data = data;
            this.minAtHere = min;
        }
    }
    private Node top;

    public void push(int val) {
        int min = 0;
        if (top != null && top.minAtHere < val) {
            min = top.minAtHere;
        } else {
            min = val;
        }
        Node n = new Node(val, min);
        n.next = top;
        top = n;
    }

    public int pop() {
        if (top == null) {
            throw new EmptyStackException();
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    public int peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getMin() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.minAtHere;
    }

    public static void main(String[] args) {
        StackMin sm = new StackMin();

        sm.push(10);
        System.out.println("Minimum here -> " + sm.getMin());

        sm.push(9);
        sm.push(11);
        System.out.println("Minimum here -> " + sm.getMin());

        sm.pop();
        System.out.println("Minimum here -> " + sm.getMin());

        sm.push(7);
        sm.push(8);
        System.out.println("Minimum here -> " + sm.getMin());

        sm.push(6);
        System.out.println("Minimum here -> " + sm.getMin());

        sm.push(1);
        System.out.println("Minimum here -> " + sm.getMin());
    }

}
