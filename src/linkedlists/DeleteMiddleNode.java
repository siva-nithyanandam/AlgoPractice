package linkedlists;

/**
 * Implement an algorithm to delete a node in the middle(i.e. any node but the first and last node,
 * not necessarily the exact middle) of a singly linked list, given only access that node.
 * Example:
 * Input: a->b->c->d->e->f
 * Output: a->b->d->e->f
 */

/**
 * Idea here is have 2 pointers, one moving one step, other moving 2 steps. When second one reaches
 * end, first would have be half the way. Delete that node!
 */

/**
 * Result:
 * 6,5,4,2,1,
 */
public class DeleteMiddleNode {

  static class Node {
    int data;
    Node next;

    Node(int data) {
      this.data = data;
    }

    void appendToTail(int data) {
      Node end = new Node(data);
      Node n = this;
      while(n.next != null) {
        n = n.next;
      }
      n.next = end;
    }

    void deleteMiddleNode() {
      Node oddPtr = this;
      Node evenPtr = this;
      Node prev = null;
      while(evenPtr != null && evenPtr.next != null) {
        prev = oddPtr;
        oddPtr = oddPtr.next;
        evenPtr = evenPtr.next.next;
      }
      prev.next = oddPtr.next;
    }
  }

  public static void main(String[] args) {
    Node linkedList = createList();
    linkedList.deleteMiddleNode();
    printValues(linkedList);
  }

  private static void printValues(Node node) {
    Node n = node;
    while(n != null) {
      System.out.print(n.data + ",");
      n = n.next;
    }
  }

  private static Node createList() {
    Node linkedList = new Node(6);
    linkedList.appendToTail(5);
    linkedList.appendToTail(4);
    linkedList.appendToTail(3);
    linkedList.appendToTail(2);
    linkedList.appendToTail(1);
    return linkedList;
  }
}
