package TreesAndGraphs;

import java.util.concurrent.atomic.AtomicInteger;
/**
 * Implement a function to check if a binary tree is balanced. For the purpose of
 * this question, a balanced binary tree is defined to be a tree such that the
 * heights of the two subtrees of any node never differ by more than one.
 */

/**
 * O(n)
 * Compare heights of left and right subtrees. Introducing AtomicInteger for tracking height.
 */
public class CheckBalanced {
    static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.left.left = new Node(3);

        System.out.println(isBalanced(head, new AtomicInteger()));
    }

    private static boolean isBalanced(Node node, AtomicInteger atomicInteger) {

        if (node == null) {
            return true;
        }

        AtomicInteger lHeight = new AtomicInteger();
        AtomicInteger rHeight = new AtomicInteger();
        boolean l = isBalanced(node.left, lHeight);
        boolean r = isBalanced(node.right, rHeight);

        if (Math.abs(lHeight.get() - rHeight.get()) > 1) {
            return false;
        }
        atomicInteger.set(1 + Math.max(lHeight.get(), rHeight.get()));
        return l && r;
    }
}
