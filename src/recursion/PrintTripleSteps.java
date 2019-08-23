package recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Print ways to reach the n’th stair taking 1, 2 or 3 hops possibilities.
 */

/**
 * Recursion problem.
 * Increment the steps of possibilities to the current step and check it reaches n'th stair.
 */
public class PrintTripleSteps {
    public static void main(String[] args) {
        printWaysToReach(6);
    }

    private static void printWaysToReach(int steps) {
        List<Integer> list = new ArrayList<Integer>();
        printWaysToReach(steps, 0, list);
    }

    private static void printWaysToReach(int steps, int curr, List<Integer> list) {
        if (curr > steps) {
            return;
        }
        list.add(curr);
        if (curr == steps) {
            for (int i = 1; i < list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println("");
            list.remove(list.size() - 1);
            return;
        }
        printWaysToReach(steps, curr + 1, list);
        printWaysToReach(steps, curr + 2, list);
        printWaysToReach(steps, curr + 3, list);
        list.remove(list.size() - 1);
    }
}
