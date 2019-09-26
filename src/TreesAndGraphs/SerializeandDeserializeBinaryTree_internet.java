package TreesAndGraphs;

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
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
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
public class SerializeandDeserializeBinaryTree_internet {

  private static char NULL_NODE = ((char)007);

  public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    TreeNode node;
    SerializeandDeserializeBinaryTree_internet o = new SerializeandDeserializeBinaryTree_internet();

    node = o.deserialize("12\u0007\u000734\u0007\u00075\u0007\u0007"); //1345
    System.out.println(o.serialize(node));

    System.out.println();
    node = o.deserialize("12\u0007\u000734\u0007\u00075\u0007\u0007");
    System.out.println(o.serialize(node));
  }

  // Encodes a tree to a single string.
  public String serialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    buildString(root, sb);
    return sb.toString();
  }

  public void buildString(TreeNode root, StringBuilder sb){
    if(root == null){
      sb.append(NULL_NODE);
      return;
    }
    sb.append((char)(root.val+'0'));
    buildString(root.left, sb);
    buildString(root.right,sb);

  }

  // Decodes your encoded data to tree.
  //int index = 0;

  public TreeNode deserialize(String data) {
    return buildTree(data.toCharArray(), new int[] {0});
  }

  public TreeNode buildTree(char[] nodes, int[] p){
    if(nodes==null ) return null;
    char root_val = nodes[p[0]++]; //remove the first one. pre-order should be the root.
    if(root_val==NULL_NODE){
      return null;
    }

    TreeNode root  = new TreeNode(Integer.valueOf(root_val-'0'));
    root.left = buildTree(nodes, p);
    root.right = buildTree(nodes, p );


    return root;
  }
}
