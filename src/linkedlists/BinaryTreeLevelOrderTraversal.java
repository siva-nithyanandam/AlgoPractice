package linkedlists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. Binary Tree Level Order Traversal
 * Given a binary tree, return the level order traversal of its TreeNodes' values.
 * (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */

/**
 * Recursion technique - Have level variable at each recursion to count and populate entry into "res"
 */
public class BinaryTreeLevelOrderTraversal {

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
    head.left.left = new TreeNode(4);
    head.left.right= new TreeNode(5);
    head.right.left = new TreeNode(6);
    head.right.right= new TreeNode(7);
    List<List<Integer>> res = levelOrder_faster(head);
    for (List<Integer> l : res) {
      for (Integer i : l) {
        System.out.print(i);
      }
      System.out.println();
    }
  }

  public static List<List<Integer>> levelOrder_faster(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    levelOrder_faster(root, res, 0);
    return res;
  }

  public static void levelOrder_faster(TreeNode root, List<List<Integer>> res, int level) {
    if (root == null) {
      return;
    }
    if (res.size() == level) {
      List<Integer> l = new ArrayList<>();
      l.add(root.val);
      res.add(l);
    } else {
      List<Integer> l = res.get(level);
      l.add(root.val);
    }
    levelOrder_faster(root.left, res, level+1);
    levelOrder_faster(root.right, res, level+1);
  }

  public static List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
      return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);

    int count;
    while (!queue.isEmpty()) {
      count = queue.size();
      List<Integer> subRes = new ArrayList<>();
      while(count > 0) {
        TreeNode tn = queue.poll();
        subRes.add(tn.val);
        if (tn.left != null) {
          queue.add(tn.left);
        }
        if (tn.right != null) {
          queue.add(tn.right);
        }
        count--;
      }
      res.add(subRes);
    }
    return res;
  }

}
