package arrays;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to
 * this area, called the "root." Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * Example 1:
 * Input: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * Output: 7
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 * Input: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobberIII {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    HouseRobberIII o = new HouseRobberIII();

    TreeNode node = new TreeNode(3);
    node.left = new TreeNode(2);
    node.right = new TreeNode(3);
    node.left.right = new TreeNode(3);
    node.right.right = new TreeNode(1);
    System.out.println(o.rob(node));

    node = new TreeNode(3);
    node.left = new TreeNode(4);
    node.right = new TreeNode(5);
    node.left.left = new TreeNode(1);
    node.left.right = new TreeNode(3);
    node.right.right = new TreeNode(1);
    System.out.println(o.rob(node));
  }

  public int rob(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int[] res = helper(root);
    return res[0];
  }

  private int[] helper(TreeNode node) {
    int[] val = new int[2];
    if (node == null) {
      return val;
    }
    int[] leftVal = helper(node.left);
    int[] rightVal = helper(node.right);
    // result[0] is robbing root, result[1] is not robbing root;
    val[1] = leftVal[0] + rightVal[0];
    val[0] = Math.max(node.val + leftVal[1] + rightVal[1], val[1]);
    return val;
  }
}
