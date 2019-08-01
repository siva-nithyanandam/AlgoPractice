package TreesAndGraphs;

/**
 * Design an algorithm and write code to find the first common ancestor of two nodes
 * in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
 * necessarily a binary search tree.
 */

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Iterate all the elements from root until finding both the elements. Have a class boolean
 * variable to check whenever either of the element found.
 */
public class FirstCommonAncestor {

    static class Node {
        private int data;
        private Node left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        Node fca = findFCA(root, 2, 4);
        System.out.println(fca.data);

        fca = findFCA(root, 4, 6);
        System.out.println(fca.data);

        fca = findFCA(root, 3, 4);
        System.out.println(fca.data);

        fca = findFCA(root, 2, 9);
        System.out.println(fca.data);
    }

    private static Node findFCA(Node node, int i, int j) {
        AtomicInteger ai = new AtomicInteger();
        Node fca = findFCA(node, i, j, ai);
        if (ai.get() >= 2) {
            return fca;
        } else {
            return null;
        }
    }

    private static Node findFCA(Node node, int i, int j, AtomicInteger ai) {
        if (node == null) {
            return null;
        }
        Node leftNode = findFCA(node.left, i, j, ai);
        Node rightNode = findFCA(node.right, i, j, ai);

        if (node.data == i) {
            ai.incrementAndGet();
            return node;
        } else if (node.data == j) {
            ai.incrementAndGet();
            return node;
        }

        if (leftNode != null && rightNode != null) {
            return node;
        }
        return leftNode != null ? leftNode : rightNode;
    }
}
