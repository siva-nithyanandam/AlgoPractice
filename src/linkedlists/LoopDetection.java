package linkedlists;

/**
 * Given a cirular linked list, implement an algorithm that returns the node at the
 * beginning of the loop.
 * DEFINITION
 * Circular linked list: A (corrupt) linked list in which a node's next pointer
 * points to an earlier node, so as to make a loop in the linked list.
 * EXAMPLE
 * Input:   A -> B -> C -> D -> E -> C(the same C as earlier)
 * Output:  C
 */

/**
 * Using Floyd's cycle finding algorithm
 * https://www.geeksforgeeks.org/detect-loop-in-a-linked-list/
 */
public class LoopDetection {

    static class Node{
        public int data;
        public Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = head.next.next.next;

        Node loopNode = detectLoop(head);
        System.out.println(loopNode.data);
    }

    private static Node detectLoop(Node head) {
        Node ptr1 = head.next;
        Node ptr2 = head.next.next;

        while(ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next.next;
        }
        ptr1 = head;
        while(ptr1.next != ptr2.next) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        //To remove a loop
        //ptr2.next = null;
        return ptr1.next;
    }
}
