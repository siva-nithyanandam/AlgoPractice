package linkedlists;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 * But the following [1,2,2,null,3,null,3] is not:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */

/**
 * O(n)
 * Idea is to compare the same Node reference of left's and right's.
 * Consider given node referenced as node1 and node2.
 * node1.left = node2.right && node1.right = node2.left?
 */
public class SymmetricTree {

    public static class Node {
        private int data;
        private Node left, right;
        
        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node tree = new Node(1);
        tree.left = new Node(2);
        tree.right = new Node(2);
        tree.left.left = new Node(3);
        tree.left.right = new Node(4);
        tree.right.left = new Node(4);
        tree.right.right = new Node(3);

        System.out.println(isSymmetric(tree));
    }

    public static boolean isSymmetric(Node root) {
        return isSymmetric(root, root);
    }

    public static boolean isSymmetric(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else if (root1.data != root2.data) {
            return false;
        } else {
            return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right, root2.left);
        }
    }
}
