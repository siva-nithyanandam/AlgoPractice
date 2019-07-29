package TreesAndGraphs;

/**
 * Write an algorithm to find the "next" node (i.e., in-order successor) of a given node
 * in a binary search tree. You may assume that each node has a link to its parent.
 */

/**
 * Successor means next incremented value not just next node in "in-order" successor.
 * Two cases:
 * #1 -> for the given node, if right node available, get left most node of that, or that node itself.
 * #2 -> if not, take parent that matches left of its node to given node.
 *
 * Result:
 * null
 * 5
 * 9
 * 6
 */
public class Successor {

  private static Node head;
  static class Node {
    private int data;
    private Node left, right, parent;

    public Node (int data) {
      this.data = data;
    }
  }

  private static Node insert(int data, Node node) {
    if (node == null) {
      return new Node(data);
    }
    if (data <= node.data) {
      node.left = insert(data, node.left);
      node.left.parent = node;
    } else {
      node.right = insert(data, node.right);
      node.right.parent = node;
    }
    return node;
  }

  public static void main(String[] args) {
    head = insert(9, head);
    insert(4, head);
    insert(3, head);
    insert(6, head);
    insert(7, head);
    insert(10, head);
    insert(5, head);
    Node res = findSuccessorNode(head.right);
    System.out.println(res != null ? res.data : res);

    res = findSuccessorNode(head.left);
    System.out.println(res != null ? res.data : res);

    res = findSuccessorNode(head.left.right.right);
    System.out.println(res != null ? res.data : res);

    res = findSuccessorNode(head.left.right.left);
    System.out.println(res != null ? res.data : res);
  }

  private static Node findSuccessorNode(Node node) {
    Node succ = null;
    Node parent = null;
    if (node == null) {
      return null;
    }
    if (node.right != null) {
      succ = node.right;
      while(succ.left != null) {
        succ = succ.left;
      }
    } else {
      parent = node.parent;
      while(null != parent && parent.left != node) {
        node = parent;
        parent = parent.parent;
      }
      succ = parent;
    }
    return succ;
  }

}
