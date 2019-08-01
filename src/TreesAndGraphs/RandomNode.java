package TreesAndGraphs;

import java.util.Random;

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

  static class DeletionPointer {
    private int val;
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

  private static int getMinElement(Node node) {
    Node res = node;
    while(res.left != null) {
      res = res.left;
    }
    return res.data;
  }

  private static Node delete(int data, Node node, DeletionPointer dp) {
    if (node == null) {
      return null;
    } else if (data < node.data) {
      node.left = delete(data, node.left, dp);
    } else if (data > node.data) {
      node.right = delete(data, node.right, dp);
    } else {
      //If 2 childrens
      if (node.left != null && node.right != null) {
        int min = getMinElement(node.right);
        node.data = min;
        node.right = delete(min, node.right, dp);
      }
      // if only left child
      else if (node.left != null) {
        node = node.left;
        dp.val = -1;
      }
      // if only right child
      else if (node.right != null) {
        node = node.right;
        dp.val = -1;
      }
      //Both childs are null
      else {
        node = null;
        dp.val = -1;
      }
    }
    if (node != null) {
      node.size += dp.val;
    }
    return node;
  }

  private static boolean delete(int data, DeletionPointer dp) {
    if (head == null) {
      return false;
    } else {
      delete(data, head, dp);
      return true;
    }
  }

  private static Node getRandomNode() {
    int totalSize = head.size + 1;
    Random random = new Random();
    int rv = random.nextInt(totalSize - 1);
    if (head != null) {
      return getGivenIndex(rv, head);
    } else {
      return null;
    }
  }

  private static Node getGivenIndex(int i, Node node) {
    if (node != null) {
      if (node.left != null && node.left.size <= i) {
        return getGivenIndex(i - node.left.size, node.left);
      } else if ()
      else {

      }
    } else {
      return null;
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

    int val;
    Node f;

    //Find
    val = 5;
    f = find(val, head);
    if (f == null) {
      System.out.println(val + " not found");
    } else {
      System.out.println(val + " was found and children size is " + f.size);
    }

    val = 9;
    f = find(val, head);
    if (f == null) {
      System.out.println(val + " not found");
    } else {
      System.out.println(val + " was found and children size is " + f.size);
    }

    val = 3;
    f = find(val, head);
    if (f == null) {
      System.out.println(val + " not found");
    } else {
      System.out.println(val + " was found and children size is " + f.size);
    }

    val = 7;
    f = find(val, head);
    if (f == null) {
      System.out.println(val + " not found");
    } else {
      System.out.println(val + " was found and children size is " + f.size);
    }

    //Delete
    DeletionPointer dp = new DeletionPointer();

    dp.val = 0;
    val = 9;
    System.out.println(val + " is deleted? - " + delete(val, dp));

    dp.val = 0;
    val = 5;
    System.out.println(val + " is deleted? - " + delete(val, dp));

    //Find
    val = 5;
    f = find(val, head);
    if (f == null) {
      System.out.println(val + " not found");
    } else {
      System.out.println(val + " was found and children size is " + f.size);
    }

    val = 9;
    f = find(val, head);
    if (f == null) {
      System.out.println(val + " not found");
    } else {
      System.out.println(val + " was found and children size is " + f.size);
    }

    val = 3;
    f = find(val, head);
    if (f == null) {
      System.out.println(val + " not found");
    } else {
      System.out.println(val + " was found and children size is " + f.size);
    }

    val = 7;
    f = find(val, head);
    if (f == null) {
      System.out.println(val + " not found");
    } else {
      System.out.println(val + " was found and children size is " + f.size);
    }

    //Random Node
    Node rn = getRandomNode();
  }
}
