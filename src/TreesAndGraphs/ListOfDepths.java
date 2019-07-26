package TreesAndGraphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

/**
 * Given a binary tree, design an algorithm which creates a linked list of all nodes at
 * each depth(e.g if you have tree with depth D, you will have D linked lists)
 */

/**
 * Create a variable assigning depth at each level and store it to Map of depth and linked list of
 * nodes. Straight forward!
 */
public class ListOfDepths {

  static class Node {
    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
      this.data = data;
    }
  }

  static class LNode {
    private int data;
    private LNode next;

    public LNode(int data) {
      this.data = data;
    }
  }

  public static void main(String[] args) {
    Node head = new Node(1);
    head.left = new Node(2);
    head.right = new Node(3);
    head.left.left = new Node(4);
    head.left.right= new Node(5);
    head.right.left = new Node(6);
    head.right.right= new Node(7);

    //Approach 1 - Traversing variable
    Map<Integer, LinkedList<Node>> map = new HashMap<>();
    getLinkedLists(head, map, 0);
    printList(map);

    //Approach 2 - Using level order traversal
    List<LNode> nodes = levelOrderTraversal(head);
    printList(nodes);
  }

  private static void printList(List<LNode> list) {
    for(LNode n : list) {
      LNode c = n;
      while(c != null) {
        System.out.print(c.data + ",");
        c = c.next;
      }
      System.out.println();
    }
  }

  private static void printList(Map<Integer, LinkedList<Node>> map) {
    for(Entry<Integer, LinkedList<Node>> list : map.entrySet()) {
      for(Node n : list.getValue()) {
        System.out.print(n.data + ",");
      }
      System.out.println();
    }
  }

  private static List<LNode> levelOrderTraversal (Node root) {
    List<LNode> list = new ArrayList<>();
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    int nbrOfElements = 0;
    while(!queue.isEmpty()) {
      nbrOfElements = queue.size();
      LNode head = null;
      LNode curr = null;
      while(nbrOfElements > 0) {
        Node n = queue.remove();
        LNode ln = new LNode(n.data);
        if (head == null) {
          head = ln;
          curr = ln;
        } else {
          curr.next = ln;
          curr = curr.next;
        }
        if (n.left != null) {
          queue.add(n.left);
        }
        if (n.right != null) {
          queue.add(n.right);
        }
        nbrOfElements--;
      }
      list.add(head);
    }
    return list;
  }

  private static void getLinkedLists(Node node, Map<Integer, LinkedList<Node>> map, int pos) {
    if (node != null) {
      LinkedList<Node> ll = map.get(pos);
      if (ll == null) {
        ll = new LinkedList<>();
      }
      ll.add(node);
      map.put(pos, ll);

      int childPos = pos + 1;
      getLinkedLists(node.left, map, childPos);
      getLinkedLists(node.right, map, childPos);
    }
  }
}
