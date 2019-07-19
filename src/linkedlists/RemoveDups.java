package linkedlists;

import java.util.ArrayList;
import java.util.List;

/**
 * Write code to remove duplicates from an unsorted linked list.
 * FOLLOW UP
 * How would you solve this problem if temporary buffer is not allowed?
 */

/**
 * #1 -> Use Buffer to find duplicates. O(n)
 * #2 -> Use two loops to find duplicates. O(n^2)
 */

/**
 * Result:
 * ****WITH BUFFER****
 * Before removing dups:
 * 3,1,2,3,4,
 * After removing dups:
 * 3,1,2,4,
 *
 * ****WITH OUT BUFFER****
 * Before removing dups:
 * 3,1,2,3,4,
 * After removing dups:
 * 3,1,2,4,
 */
public class RemoveDups {

  static class Node {

    int data;
    Node next;

    public Node(int data) {
      this.data = data;
    }

    void appendToTail(int data) {
      Node end = new Node(data);
      Node n = this;
      while (n.next != null) {
        n = n.next;
      }
      n.next = end;
    }
  }

  public static void main(String[] args) {
    Node n;
    Node linkedList = createList();
    System.out.println("****WITH BUFFER****");
    System.out.println("Before removing dups:");
    printValues(linkedList);

    removeDuplicatesWithBuffer(linkedList);

    System.out.println("");
    System.out.println("After removing dups:");
    printValues(linkedList);
    System.out.println("");
    System.out.println("");

    linkedList = createList();
    System.out.println("****WITH OUT BUFFER****");
    System.out.println("Before removing dups:");
    printValues(linkedList);

    removeDuplicatesWithoutBuffer(linkedList);

    System.out.println("");
    System.out.println("After removing dups:");
    printValues(linkedList);
  }

  private static Node createList() {
    Node linkedList = new Node(3);
    linkedList.appendToTail(1);
    linkedList.appendToTail(2);
    linkedList.appendToTail(3);
    linkedList.appendToTail(4);
    return linkedList;
  }

  private static void removeDuplicatesWithoutBuffer(Node linkedList) {
    Node ptr1 = linkedList;
    Node ptr2;
    while(ptr1 != null) {
      ptr2 = ptr1;
      while(ptr2.next != null) {
        if(ptr1.data == ptr2.next.data) {
          ptr2.next = ptr2.next.next;
        } else {
          ptr2 = ptr2.next;
        }
      }
      ptr1 = ptr1.next;
    }
  }


  private static void removeDuplicatesWithBuffer(Node linkedList) {
    List<Integer> arr = new ArrayList<>();
    Node n = linkedList;
    Node prev = null;
    while(n != null) {
      if(arr.contains(n.data)) {
        prev.next = n.next;
      } else {
        arr.add(n.data);
        prev = n;
      }
      n = n.next;
    }
  }

  private static void printValues(Node node) {
    Node n = node;
    while(n != null) {
      System.out.print(n.data + ",");
      n = n.next;
    }
  }
}
