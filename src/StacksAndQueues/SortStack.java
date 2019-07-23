package StacksAndQueues;

import java.util.Stack;

/**
 * Write a program to sort a stack such that the smallest items are on the top. You can use
 * an additional temporary stack, but you may not copy the elements into any other data structure
 * (such as an array). The stack supports the following operations: push, pop, peek, and isEmpty.
 */

/**
 * Use temp variable to switch the greater/smaller elements in the inputStack and store it in
 * outputStack.
 * Result:
 * Before Sorting:
 * 34
 * 3
 * 31
 * 98
 * 92
 * 23
 *
 * After Sorting:
 * 98
 * 92
 * 34
 * 31
 * 23
 * 3
 */
public class SortStack {

  private static Stack<Integer> doSorting(Stack<Integer> inputStack) {

    int temp;
    Stack<Integer> outputStack = new Stack<>();

    while(!inputStack.isEmpty()) {
      temp = inputStack.pop();
      if(outputStack.isEmpty() || outputStack.peek() > temp) {
        outputStack.push(temp);
      } else {
        inputStack.push(outputStack.pop());
        inputStack.push(temp);
      }
    }
    return outputStack;
  }

  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.add(34);
    stack.add(3);
    stack.add(31);
    stack.add(98);
    stack.add(92);
    stack.add(23);

    System.out.println("Before Sorting:");
    printStackData(stack);

    Stack<Integer> temp = doSorting(stack);

    System.out.println("");
    System.out.println("After Sorting:");
    printStackData(temp);
  }

  private static void printStackData(Stack<Integer> stack) {
    for(Integer s : stack) {
      System.out.println(s);
    }
  }

}
