package linkedlists;

public class IsThereALoopDetection {

  static class Node {
    int key;
    Node next;
  };

  static Node newNode(int key)
  {
    Node temp = new Node();
    temp.key = key;
    temp.next = null;
    return temp;
  }

  // A utility function to print a linked list
  static void printList(Node head)
  {
    while (head != null) {
      System.out.print(head.key + " ");
      head = head.next;
    }
    System.out.println();
  }

  // Function to detect first node of loop
  // in a linked list that may contain loop
  static boolean detectLoop(Node head)
  {

    // Create a temporary node
    Node temp = new Node();
    while (head != null) {

      // This condition is for the case
      // when there is no loop
      if (head.next == null) {
        return false;
      }

      // Check if next is already
      // pointing to temp
      if (head.next == temp) {
        return true;
      }

      // Store the pointer to the next node
      // in order to get to it in the next step
      Node nex = head.next;

      // Make next point to temp
      head.next = temp;

      // Get to the next node in the list
      head = nex;
    }

    return false;
  }

  // Driver code
  public static void main(String args[])
  {
    Node head = newNode(1);
    head.next = newNode(2);
    head.next.next = newNode(3);
    head.next.next.next = newNode(4);
    head.next.next.next.next = newNode(5);

    // Create a loop for testing(5 is pointing to 2) /
    head.next.next.next.next.next = head.next;

    boolean found = detectLoop(head);
    if (found)
      System.out.println("Loop Found");
    else
      System.out.println("No Loop");
  }
}

// This code is contributed by Arnab Kundu
