package TreesAndGraphs;

/**
 * You are implementing a binary tree class from scratch which, in addition to find, insert and
 * delete, has a method getRandomNode() which returns a random node from the tree. All nodes
 * should be equally likely to be chosen. Design and implement an algorithm for getRandomNode,
 * and explain how you would implement the rest of the methods.
 */
public class RandomNode {

  private static Node head;

  static class Node {
    private int data;
    private int size;
    private Node left, right;

    public Node (int data) {
      this.data = data;
    }

    private static Node insertInOrder(int data, Node node) {
      if (node == null) {
        return new Node(data);
      }
      if (data <= node.data) {
        node.left = insertInOrder(data, node.left);
      } else {
        node.right = insertInOrder(data, node.right);
      }
      node.size++;
      return node;
    }
  }

  private static void insertInOrder(int data) {
    if (head == null) {
      head = new Node(data);
    } else {
      head.insertInOrder(data, head);
    }
  }

  private static Node find(int data, Node node) {
    if (node == null) {
      return null;
    } else if (node.data == data) {
      return node;
    } else {
      if (data <= node.data) {
        return find(data, node.left);
      } else {
        return find(data, node.right);
      }
    }
  }

  private static int delete(int data, Node node) {
    int v;
    if (node == null) {
      v = 0;
    } else if (node.data == data) {
      v = -1;
    } else {
      if (data <= node.data) {
        v = delete(data, node.left);
      } else {
        v = delete(data, node.right);
      }
    }
    node.size += v;
    return v;
  }

  private static boolean delete(int data) {
    if (head == null) {
      return false;
    } else {
      int v = delete(data, head);
      return v == -1 ? true : false;
    }
  }

  public static void main(String[] args) {
    //Insert
    insertInOrder(5);
    insertInOrder(3);
    insertInOrder(7);
    insertInOrder(4);
    insertInOrder(2);
    insertInOrder(1);
    insertInOrder(10);
    insertInOrder(9);

    //Find
    Node f = find(2, head);
    System.out.println("Found 2, and the size is - " + f == null? null : f.data);

    f = find(5, head);
    System.out.println("Found 2, and the size is - " + f == null? null : f.data);

    f = find(12121, head);
    if (f == null) {
      System.out.println("data not found");
    } else {
      System.out.println("Found 12121, and the size is - " + f.data);
    }

    //Delete
    System.out.println("Is deleted? - " + delete(9));

    //Find
    f = find(2, head);
    System.out.println("Found 2, and the size is - " + f == null? null : f.data);

    f = find(5, head);
    System.out.println("Found 2, and the size is - " + f == null? null : f.data);

    f = find(12121, head);
    if (f == null) {
      System.out.println("data not found");
    } else {
      System.out.println("Found 12121, and the size is - " + f.data);
    }
  }

  private static int assignSize(Node node) {
    if (node == null) {
      return 0;
    } else {
      node.size = assignSize(node.left) + assignSize(node.right) + 1;
    }
    return node.size;
  }
}
