package TreesAndGraphs;

/**
 * Given a sorted(increasing order) array with unique integer elements, write an algorithm
 * to create a binary search tree with minimal height.
 */
public class MinimalTree {

    static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    private static Node createBST(int[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        int n = (start + end)/2;
        Node root = new Node(arr[n]);
        root.left = createBST(arr, start, n - 1);
        root.right = createBST(arr, n + 1, end);
        return root;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        Node result = createBST(arr, 0, arr.length-1);
        preOrderTraversal(result);
    }

    private static void preOrderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.data + ",");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }
}
