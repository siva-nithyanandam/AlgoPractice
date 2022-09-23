package TreesAndGraphs;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/4/2021  8:17 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/explore/interview/card/google/61/trees-and-graphs/3071/
 *
 * Given the root of a complete binary tree, return the number of the nodes in the tree.
 *
 * According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree,
 * and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 *
 * Design an algorithm that runs in less than O(n) time complexity.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6]
 * Output: 6
 * Example 2:
 *
 * Input: root = []
 * Output: 0
 * Example 3:
 *
 * Input: root = [1]
 * Output: 1
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 5 * 104].
 * 0 <= Node.val <= 5 * 104
 * The tree is guaranteed to be complete.
 */

 // Definition for a binary tree node.
  class TreeNode {
      int val;
      TreeNode1 left;
      TreeNode1 right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode1 left, TreeNode1 right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
public class CountCompleteTreeNodes {

  public static void main(String[] args) {
    CountCompleteTreeNodes o = new CountCompleteTreeNodes();
    System.out.println();
  }

  public int countNodes(TreeNode1 root) {

    int h = getLeftHeight(root);

    if (h < 2) {
      return h;
    }

    int totalLeafsCount = (int)Math.pow(2, h-1);

    int left = 1, right = totalLeafsCount-1;

    while (left <= right) {
      int mid = left + (right - left)/2;

      if (isExists(mid, totalLeafsCount-1, root)) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return (int)Math.pow(2, h-1) - 1 + left;
  }

  private boolean isExists(int pivot, int right, TreeNode1 node) {

    int left = 0;
    while (left < right) {
      int mid = left + (right - left)/2;
      if (pivot <= mid) {
        node = node.left;
        right = mid;
      } else {
        node = node.right;
        left = mid + 1;
      }
    }
    return node != null;
  }

  private int getLeftHeight(TreeNode1 node) {
    if (node == null) {
      return 0;
    }
    return 1 + getLeftHeight(node.left);
  }
}
