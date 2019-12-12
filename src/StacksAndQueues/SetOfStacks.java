package StacksAndQueues;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Imagine a (literal) stack of plates. If the stack gets too high, it might topple.
 * Therefore, in real life, we would likely start a new stack when the previous stack exceeds some
 * threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be
 * composed of several stacks and should create a new stack once the previous one exceeds capacity.
 * SetOfStacks.push () and SetOfStacks.pop () should behave identically to a single stack
 * (that is, pop() should return the same values as it would if there were just a single stack).
 * FOLLOW UP
 * Implement a function popAt (int index) which performs a pop operation on a specific sub-stack.
 */

/**
 * Generic solution. Move elements accordingly.
 * Result:
 * 1 2 3 [TOP]
 * 4 5 6 [TOP]
 * 7 8 9 [TOP]
 *
 * 1 2 4 [TOP]
 * 5 7 8 [TOP]
 * 9 [TOP]
 */
public class SetOfStacks {

  private static final int STACK_SIZE = 3;
  private LinkedList<Stack<Integer>> stacks = new LinkedList<>();

  public void push(int data) {
    if(stacks.isEmpty() || stacks.getLast().size() == STACK_SIZE) {
      Stack<Integer> stack = new Stack<>();
      stack.push(data);
      stacks.add(stack);
    } else {
      stacks.getLast().push(data);
    }
  }

  public int pop() {
    if(stacks.isEmpty()) {
      throw new EmptyStackException();
    }
    int result = stacks.getLast().pop();
    removeStackIfEmpty();
    return result;
  }

  public int popAt(int index) {
    if (index > stacks.size()) {
      throw new IllegalStateException();
    }
    int data = stacks.get(index).pop();
    for(int i = index; i < stacks.size() - 1; i++) {
      Stack<Integer> current = stacks.get(i);
      Stack<Integer> next = stacks.get(i+1);
      current.push(next.remove(0));
    }
    removeStackIfEmpty();
    return data;
  }

  public void printStacks() {
    for (Stack<Integer> stack : stacks) {
      for (int item : stack) {
        System.out.print(item + " ");
      }
      System.out.println("[TOP]");
    }
  }

  private void removeStackIfEmpty() {
    if (stacks.getLast().size() == 0) {
      stacks.removeLast();
    }
  }

  public static void main(String[] args) {
    SetOfStacks setOfStacks = new SetOfStacks();
    setOfStacks.push(1); setOfStacks.push(2); setOfStacks.push(3);
    setOfStacks.push(4); setOfStacks.push(5); setOfStacks.push(6);
    setOfStacks.push(7); setOfStacks.push(8); setOfStacks.push(9);
    setOfStacks.printStacks();
    System.out.println();
    setOfStacks.popAt(1);
    setOfStacks.popAt(0);
    setOfStacks.printStacks();
  }
}
