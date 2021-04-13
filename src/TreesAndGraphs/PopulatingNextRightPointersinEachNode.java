package TreesAndGraphs;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 *
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Follow up:
 *
 * You may only use constant extra space.
 * Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.
 *
 *
 * Example 1:
 *
 *
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 *
 *
 * Constraints:
 *
 * The number of nodes in the given tree is less than 4096.
 * -1000 <= node.val <= 1000
 */
public class PopulatingNextRightPointersinEachNode {

  static class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
      val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
      val = _val;
      left = _left;
      right = _right;
      next = _next;
    }
  };

  public static void main(String[] args) {
    PopulatingNextRightPointersinEachNode o = new PopulatingNextRightPointersinEachNode();

    Node tree = new Node(1);
    tree.left = new Node(2);
    tree.right = new Node(3);
    tree.left.left = new Node(4);
    tree.right.right = new Node(5);

    o.connectRecur(tree);
  }

  public Node connect(Node node) {
    if (node == null) {
      return null;
    }
    if (node.left != null) {
      node.left.next = node.right;
      node.right.next = getAdjacentNode(node);
      connect(node.left);
    }
    if (node.right != null) {
      node.right.next = getAdjacentNode(node);
      connect(node.right);
    }
    return node;
  }

  private Node getAdjacentNode(Node node) {
    Node temp = node.next;
    if (temp != null) {
      if (temp.left != null) {
        return temp.left;
      } else {
        return temp.right;
      }
    }
    return null;
  }

  void connectRecur(Node p) {
    // Base case
    if (p == null) {
      return;
    }
    /* Before setting next of left and right children, set next
       of children of other nodes at same level (because we can access
       children of other nodes using p's next only) */
    if (p.next != null) {
      connectRecur(p.next);
    }
    /* Set the next pointer for p's left child */
    if (p.left != null)
    {
      if (p.right != null)
      {
        p.left.next = p.right;
        p.right.next = getNextRight(p);
      }
      else {
        p.left.next = getNextRight(p);
      }
      /* Recursively call for next level nodes.  Note that we call only
       for left child. The call for left child will call for right child */
      connectRecur(p.left);
    }
    /* If left child is NULL then first node of next level will either be
     p->right or getNextRight(p) */
    else if (p.right != null)
    {
      p.right.next = getNextRight(p);
      connectRecur(p.right);
    }
    else {
      connectRecur(getNextRight(p));
    }
  }

  /* This function returns the leftmost child of nodes at the same
     level as p. This function is used to getNExt right of p's right child
     If right child of p is NULL then this can also be used for 
     the left child */
  Node getNextRight(Node p) {
    Node temp = p.next;
   
    /* Traverse nodes at p's level and find and return
     the first node's first child */
    while (temp != null) {
      if (temp.left != null) {
        return temp.left;
      }
      if (temp.right != null) {
        return temp.right;
      }
      temp = temp.next;
    }
    // If all the nodes at p's level are leaf nodes then return NULL
    return null;
  }
}
