package TreesAndGraphs;

/**
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty,
 * flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 * Example:
 * Input: [1,2,3,4,5]
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * Output: return the root of the binary tree [4,5,2,#,#,3,1]
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 */

/**
 * 2 Solutions. Iterative and Recursive.
 * Better Go through them.
 */
public class BinaryTreeUpsideDown {

  static class Node {
    private int data;
    private Node left, right;

    public Node(int data) {
      this.data = data;
    }
  }

  public static void main(String[] args) {
    BinaryTreeUpsideDown o = new BinaryTreeUpsideDown();
    Node node = new Node(1);
    node.left = new Node(2);
    node.left.left = new Node(4);
    node.left.right = new Node(5);
    node.right = new Node(3);

    Node resNode = o.upsideDown(node);
    System.out.println(resNode.data);
  }

  private Node upsideDown(Node head) {
    if (head == null || head.left == null) {
      return head;
    }
    Node lastRight = null;
    Node curr = head;
    Node prev = null;
    Node next = null;
    while (curr != null) {
      next = curr.left;
      curr.left = lastRight;
      lastRight = curr.right;
      curr.right = prev;
      prev = curr;
      curr = next;
    }
    return prev;
  }

  private Node upsideDown_recursive(Node head) {
    if (head == null || head.left == null) {
      return head;
    }
    Node newRoot = upsideDown_recursive(head.left);
    head.left.left = head.right;
    head.left.right = head;
    head.left = null;
    head.right = null;
    return newRoot;
  }
}
