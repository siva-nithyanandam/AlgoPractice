package TreesAndGraphs;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given a binary tree in which each node contains an integer value(which might be positive
 * or negative). Design an algorithm to count the number of paths that sum to a given value. The
 * path does not need to start or end at the root or a leaf, but it must go downwards (travelling
 * only from parent nodes to child nodes).
 */

/**
 * Add the nodes in pre-order fashion and sum the nodes backwards until desired sum.
 * Results:
 * 5 2 1 5 -5
 * 2 1 5
 * 5 2 1
 * 3 5
 * 3 4 1
 * 3 5
 * 5 3
 * 8
 * Count for 8 is 8
 * 5
 * 5
 * 2 3
 * 4 1
 * 5
 * 5
 * Count for 5 is 6
 * 2 1 5 -5
 * 2 1
 * 3
 * 3
 * Count for 3 is 4
 */
public class PathsWithSum {

  static class Node {
    private int data;
    private Node left, right;

    public Node(int data) {
      this.data = data;
    }
  }

  public static void main(String[] args) {
    Node root = new Node(8);
    root.left = new Node(5);
    root.left.left = new Node(2);
    root.left.left.left = new Node(1);
    root.left.left.left.left = new Node(5);
    root.left.left.left.left.left = new Node(-5);
    root.left.left.right = new Node(3);
    root.left.left.right.left = new Node(5);
    root.left.right = new Node(3);
    root.left.right.left = new Node(4);
    root.left.right.right = new Node(5);
    root.left.right.left.left = new Node(1);
    root.right = new Node(7);

    System.out.println("Count for 8 is " + getPathsWithSumCount(root, 8));
    System.out.println("Count for 5 is " + getPathsWithSumCount(root, 5));
    System.out.println("Count for 3 is " + getPathsWithSumCount(root, 3));
  }

  private static List<Integer> paths = new ArrayList<>();

  private static int getPathsWithSumCount(Node node, int k) {
    int count = 0;
    if (node == null) {
      return count;
    }
    paths.add(node.data);
    count += getPathsWithSumCount(node.left, k);
    count += getPathsWithSumCount(node.right, k);

    int sum = 0;
    for(int i = paths.size()-1; i >= 0; i--) {
      sum += paths.get(i);
      if (sum == k) {
        count++;
        printPaths(paths, i);
      }
    }
    paths.remove(paths.size() - 1);
    return count;
  }

  private static void printPaths(List<Integer> ps, int i) {
    for(int j = i; j < ps.size(); j++) {
      System.out.print(ps.get(j) + " ");
    }
    System.out.println("");
  }
}
