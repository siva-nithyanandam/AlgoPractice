package TreesAndGraphs;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implement a function to check if a binary tree is a binary search tree.
 */

/**
 * Idea is to do "InOrder traversal". Store the previous value in class level and
 * compare in next recursion.
 */
public class ValidateBST {

    private static Node prevNode = null;

    static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right = new Node(5);
        head.right.right = new Node(6);

        System.out.println(isValidBST(head));
    }


    private static boolean isValidBST(Node node) {
        if (node == null) {
            return true;
        }
        else {
            //Checking left
            if (!isValidBST(node.left)) {
                return false;
            }
            //Checking center
            else if(prevNode != null && prevNode.data >= node.data) {
                return false;
            }
            //Always set center value as prev value as it is doing "Inorder" traversal
            prevNode = node;
            //Checking right
            return isValidBST(node.right);
        }
    }
}
