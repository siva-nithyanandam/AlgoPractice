package linkedlists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */

/**
 * Recursion technique - Have level variable at each recursion to count and use as a odd/even switch
 * to decide left to right OR right to left.
 *
 */
public class BinaryTreeZigzagLevelOrderTraversal {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

  public static void main(String[] args) {

    BinaryTreeZigzagLevelOrderTraversal c = new BinaryTreeZigzagLevelOrderTraversal();
    TreeNode head = new TreeNode(1);
    head.left = new TreeNode(2);
    head.right = new TreeNode(3);
    head.right.left = new TreeNode(6);
    head.right.right= new TreeNode(7);
    List<List<Integer>> res = c.zigzagLevelOrder(head);
    for (List<Integer> l : res) {
      for (Integer i : l) {
        System.out.print(i);
      }
      System.out.println();
    }
  }

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    zigzagLevelOrder(root, res, 0);
    return res;
  }

  private void zigzagLevelOrder(TreeNode root, List<List<Integer>> res, int level) {
    if (root == null) {
      return;
    }
    if (res.size() == level) {
      res.add(new ArrayList<>());
    }
    if ((level & 1) == 0) {
      res.get(level).add(root.val);
    } else {
      res.get(level).add(0, root.val);
    }
    zigzagLevelOrder(root.left, res, level+1);
    zigzagLevelOrder(root.right, res, level+1);
  }
}
