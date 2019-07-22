package linkedlists;

/**
 * Given two (singly) linked lists, determine if the two lists intersect. Return
 * the intersecting node.Note that the intersection is defined based on reference, not
 * value. That is, if the kth node of the first linked list is the exact same node
 * (by reference) as the jth node of the second linked list, then they are intersecting.
 */

/**
 * Find difference in length, and ignore those difference in big list and compare
 * one element after the other until it reaches end(bcoz, not data, its reference comparison)
 */
class Intersection {

    static class Node {

        int data;
        Node next;

        Node(int d)
        {
            data = d;
            next = null;
        }
    }

    public static void main(String[] args)
    {
        // creating first linked list
        Node head1 = new Node(3);
        head1.next = new Node(6);
        head1.next.next = new Node(17);
        head1.next.next.next = new Node(15);
        head1.next.next.next.next = new Node(30);

        // creating second linked list
        Node head2 = new Node(10);
        head2.next = new Node(6);
        head2.next.next = new Node(16);
        head2.next.next.next = new Node(15);
        head2.next.next.next.next = new Node(30);

        Node result = getIntersectingNode(head1, head2);
        if (result != null) {
            System.out.println("The node of intersection is " + result.data);
        } else {
            System.out.println("No intersection point");
        }
    }

    private static Node getIntersectingNode(Node head1, Node head2) {
        int m = getSize(head1);
        int n = getSize(head2);

        Node h1, h2, result = null;
        int diff;
        if (m > n) {
            h1 = head1;
            h2 = head2;
            diff = m - n;
        } else {
            h1 = head2;
            h2 = head1;
            diff = n - m;
        }

        while(diff-- > 0) {
            h1 = h1.next;
        }
        boolean isSet = false;
        while(h1 != null) {
            if (h1.data == h2.data) {
                if(!isSet) {
                    result = h1;
                    isSet = true;
                }
            } else {
                isSet = false;
                result = null;
            }
            h1 = h1.next;
            h2 = h2.next;
        }
        return result;
    }

    private static int getSize(Node n) {
        int size = 0;
        Node temp = n;
        while(temp != null) {
            temp = temp.next;
            size++;
        }
        return size;
    }
}

// This code has been contributed by Mayank Jaiswal
