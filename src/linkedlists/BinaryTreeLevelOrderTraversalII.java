package linkedlists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * return its bottom-up level order traversal as:
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 */
public class BinaryTreeLevelOrderTraversalII {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
  }

  public static void main(String[] args) {
    TreeNode head = new TreeNode(1);
    head.left = new TreeNode(2);
    head.right = new TreeNode(3);
    head.right.left = new TreeNode(6);
    head.right.right= new TreeNode(7);
    List<List<Integer>> res = levelOrderBottom(head);
    for (List<Integer> l : res) {
      for (Integer i : l) {
        System.out.print(i);
      }
      System.out.println();
    }
  }

  public static List<List<Integer>> levelOrderBottom(TreeNode root) {
    LinkedList<List<Integer>> res = new LinkedList<>();
    levelOrderBottom(root, res, 0);
    return res;
  }

  public static void levelOrderBottom(TreeNode root, LinkedList<List<Integer>> res, int level) {
    if (root == null) {
      return;
    }
    if (res.size() == level) {
      List<Integer> l = new ArrayList<>();
      l.add(root.val);
      res.add(0, l);
    } else {
      List<Integer> l = res.get(res.size() - level - 1);
      l.add(root.val);
    }
    levelOrderBottom(root.left, res, level+1);
    levelOrderBottom(root.right, res, level+1);
  }
}
