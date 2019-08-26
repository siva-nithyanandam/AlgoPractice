package MathAndLogic;

import java.util.Stack;

/**
 * In the classic problem of the Towers of Hanoi, you have 3 towers and N disks of different sizes
 * which can slide onto any tower. The puzzle starts with disks sorted in ascending order of size
 * from top to bottom (Le., each disk sits on top of an even larger one).
 * You have the following constraints:
 * (1) Only one disk can be moved at a time.
 * (2) A disk is slid off the top of one tower onto another tower.
 * (3) A disk cannot be placed on top of a smaller disk.
 * Write a program to move the disks from the first tower to the last using stacks.
 */
public class TowersOfHanoi {
    public static void main(String[] args) {
        Stack<Integer> fromStack = new Stack<>();
        Stack<Integer> auxStack = new Stack<>();
        Stack<Integer> toStack = new Stack<>();
        fromStack.push(4);
        fromStack.push(3);
        fromStack.push(2);
        fromStack.push(1);
        towerOfHanoi(4, fromStack, auxStack, toStack);
        for (int i = toStack.size() - 1; i >= 0; i--) {
            System.out.println(toStack.get(i));
        }
    }

    private static void towerOfHanoi(int n, Stack<Integer> fromStack, Stack<Integer> auxStack, Stack<Integer> toStack) {
        if (n > 0) {
            towerOfHanoi(n-1, fromStack, toStack, auxStack);
            toStack.push(fromStack.pop());
            towerOfHanoi(n-1, auxStack, fromStack, toStack);
        }
    }
}
