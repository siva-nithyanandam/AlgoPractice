package TreesAndGraphs;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer,
 * or transmitted across a network connection link to be reconstructed later in the same or
 * another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and this string can
 * be deserialized to the original tree structure.
 * Example:
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format,
 * so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeandDeserializeBinaryTree {

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    SerializeandDeserializeBinaryTree o = new SerializeandDeserializeBinaryTree();
    TreeNode node;

    node = o.deserialize("1,2,#,#,3,4,#,#,5,#,#");
    System.out.println(o.serialize(node));

    System.out.println();
    node = o.deserialize("1,2,#,6,#,#,3,4,#,#,5,#,#");
    System.out.println(o.serialize(node));
  }

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    depthFirstTraversal(root, sb);
    return sb.substring(0, sb.length() - 1);
  }

  private void depthFirstTraversal(TreeNode root, StringBuilder sb) {
    if (root == null) {
      sb.append("#").append(",");
      return;
    }
    sb.append(root.val).append(",");
    depthFirstTraversal(root.left, sb);
    depthFirstTraversal(root.right, sb);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data == null || data.length() == 0) {
      return null;
    }
    String[] datas = data.split(",");
    return depthFirstTraversal(datas, new int[]{0});
  }

  private TreeNode depthFirstTraversal(String[] datas, int[] idx) {
    String val = datas[idx[0]++];
    if (val.equals("#")) {
      return null;
    }
    TreeNode node = new TreeNode(Integer.parseInt(val));
    node.left = depthFirstTraversal(datas, idx);
    node.right = depthFirstTraversal(datas, idx);
    return node;
  }
}
