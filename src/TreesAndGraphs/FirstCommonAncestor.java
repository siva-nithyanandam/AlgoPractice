package TreesAndGraphs;

/**
 * Design an algorithm and write code to find the first common ancestor of two nodes
 * in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
 * necessarily a binary search tree.
 */
public class FirstCommonAncestor {
    private static boolean leftCheck;
    private static boolean rightCheck;

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

        Node fca = findFCA(root, 4, 5);
        System.out.println(fca.data);

        fca = findFCA(root, 4, 6);
        System.out.println(fca.data);

        fca = findFCA(root, 3, 4);
        System.out.println(fca.data);

        fca = findFCA(root, 2, 4);
        System.out.println(fca.data);
    }

    private static Node findFCA(Node node, int i, int j) {
        if (node == null) {
            return null;
        }

        if (node.data == i) {
            leftCheck = true;
            return node;
        } else if (node.data == j) {
            rightCheck = true;
            return node;
        }

        Node leftNode = findFCA(node.left, i, j);
        Node rightNode = findFCA(node.right, i, j);

        if (leftNode != null && rightNode != null) {
            return node;
        }
        return leftNode != null ? leftNode : rightNode;
    }
}
