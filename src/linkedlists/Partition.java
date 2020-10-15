package linkedlists;

/**
 * Write code eto partition a linkedlist around a value x,such that all nodes less than
 * x come before all nodes greater than or equal to x. If x is contained within
 * the list, the value of x only need to be after the elements less than x(see below).
 * The partition element can appear anywhere in the "right partition"; it does not need to
 * appear between the left and right partitions.
 * Example:
 * Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1 [partition = 5]
 * Output: 1 -> 2 -> 3 -> 5 -> 8 -> 5 -> 10
 */

/**
 * Results:
 * Input: 3 5 8 5 10 2 1 [x = 6]
 * Output: 1 2 5 5 3 8 10
 */
class Partition {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(3);
        head.next = new Node(5);
        head.next.next = new Node(8);
        head.next.next.next = new Node(5);
        head.next.next.next.next = new Node(10);
        head.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next = new Node(11);

        printList(head);
        head = partition(head, 5);
        printList(head);
    }

    private static Node partition(Node head, int x) {
        Node tail = head;
        Node curr = head;
        Node next;
        while(curr != null) {
            next = curr.next;
            if (curr.data < x) {
                curr.next = head;
                head = curr;
            } else {
                tail.next = curr;
                tail = curr;
            }
            curr = next;
        }
        tail.next = null;
        return head;
    }

    private static void printList(Node head) {
        Node n = head;
        while(n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println("");
    }
}

// This code is contributed by prerna saini

