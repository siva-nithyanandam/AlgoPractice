package linkedlists;

/**
 * Implement an algorithm to find the Kth to last element of a singly linked list.
 */

/**
 * Idea is to use 2 pointers(ptr1 and ptr2). Move ptr2 to Kth index from start.
 * Then move both ptr1(from 0), ptr2(from k) at the same speed until ptr2 reaches end.
 * Now ptr1 is pointing Kth element from the end!
 */
public class ReturnKthToLast {

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

    Node returnKthElementFromLast(int k) {
      Node ptr1 = this;
      Node ptr2 = this;
      for (int i = 1; i <= k; i++) {
        ptr2 = ptr2.next;
      }
      while(ptr2.next != null) {
        ptr2 = ptr2.next;
        ptr1 = ptr1.next;
      }
      return ptr1;
    }
  }

  public static void main(String[] args) {
    Node linkedList = createList();
    Node ptr = linkedList.returnKthElementFromLast(3);
    System.out.println(ptr.data);
    ptr = linkedList.returnKthElementFromLast(1);
    System.out.println(ptr.data);
    ptr = linkedList.returnKthElementFromLast(0);
    System.out.println(ptr.data);
    ptr = linkedList.returnKthElementFromLast(7);
    System.out.println(ptr.data);
  }

  private static Node createList() {
    Node linkedList = new Node(8);
    linkedList.appendToTail(7);
    linkedList.appendToTail(6);
    linkedList.appendToTail(5);
    linkedList.appendToTail(4);
    linkedList.appendToTail(3);
    linkedList.appendToTail(2);
    linkedList.appendToTail(1);
    return linkedList;
  }

}
