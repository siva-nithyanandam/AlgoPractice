package linkedlists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * Note: A leaf is a node with no children.
 * Example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its depth = 3.
 */
public class MaximumDepthofBinaryTree {

  public static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

  public static void main(String[] args) {
    MaximumDepthofBinaryTree o = new MaximumDepthofBinaryTree();
    TreeNode head = new TreeNode(1);
    head.left = new TreeNode(2);
    head.right = new TreeNode(3);
    head.left.left = new TreeNode(4);
    head.left.right= new TreeNode(5);
    head.right.left = new TreeNode(6);
    head.right.right= new TreeNode(7);
    System.out.println(o.maxDepth(head));
  }

  public int maxDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int lh = maxDepth(root.left);
    int rh = maxDepth(root.right);
    return (lh > rh ? lh : rh) + 1;
  }

}
