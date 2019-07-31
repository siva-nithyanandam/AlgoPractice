package TreesAndGraphs;

/**
 * T1 and T2 are two very large binary  with T1 much bigger than T2. Create an algorithm
 * to determine if T2 is a subof T1.
 * A T2 is a subof T1 if there exists a node n in T1 such that the subof n is
 * identical to T2. That is, if you cutt off te at node n, the two  would be
 * identical.
 */

/**
 * Consider each node from big tree as root and compare with small tree root. Look for Null
 * pointer carefully.
 */
public class CheckSubtree{

  static class Node {
    private int data;
    private Node left, right;

    public Node(int data) {
      this.data = data;
    }
  }

  public static void main(String[] args) {
    // 1 
        /* Construct the following 
              26 
             /   \ 
            10     3 
           /    \     \ 
          4      6      3 
           \ 
            30  */

    Node root1 = new Node(26);
    root1.right = new Node(3);
    root1.right.right = new Node(3);
    root1.left = new Node(10);
    root1.left.left = new Node(4);
    root1.left.left.right = new Node(30);
    root1.left.right = new Node(6);

    // 2 
        /* Construct the following 
           10 
         /    \ 
         4      6 
          \ 
          30  */

    Node root2 = new Node(10);
    root2.right = new Node(6);
    root2.left = new Node(4);
    root2.left.right = new Node(30);

    System.out.println(isSubtree(root1, root2));

    Node root3 = new Node(10);
    root3.right = new Node(6);
    root3.left = new Node(4);
    root3.left.right = new Node(31);

    System.out.println(isSubtree(root1, root3));
  }

  private static boolean isSubtree(Node root1, Node root2) {
    if (areIdentical(root1, root2)) {
      return true;
    } else if (root1 == null || root2 == null) {
      return false;
    } else {
      return isSubtree(root1.left, root2) || isSubtree(root1.right, root2);
    }
  }

  private static boolean areIdentical(Node root1, Node root2) {
    if (root1 == null && root2 == null) {
      return true;
    } else if (root1 == null || root2 == null) {
      return false;
    } else {
      return root1.data == root2.data
          && areIdentical(root1.left, root2.left)
          && areIdentical(root1.right, root2.right);
    }
  }
}
