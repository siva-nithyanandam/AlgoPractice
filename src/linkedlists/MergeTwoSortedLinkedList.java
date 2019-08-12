package linkedlists;

import java.util.List;
import java.util.LinkedList;
/**
 * Merge two sorted linked lists
 * Write a SortedMerge() function that takes two lists, each of which is sorted in increasing order,
 * and merges the two together into one list which is in increasing order.
 * SortedMerge() should return the new list. The new list should be made by splicing
 * together the nodes of the first two lists.
 *
 * For example if the first linked list a is 5->10->15 and the other linked list b is 2->3->20,
 * then SortedMerge() should return a pointer to the head node of the merged list 2->3->5->10->15->20.
 *
 * There are many cases to deal with: either ‘a’ or ‘b’ may be empty,
 * during processing either ‘a’ or ‘b’ may run out first, and finally there’s the problem of starting the result list empty,
 * and building it up while going through ‘a’ and ‘b’.
 */

/**
 * Use recursion.
 */
public class MergeTwoSortedLinkedList {

    static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }

        public void add(int data) {
            Node n = this;
            while(n.next != null) {
                n = n.next;
            }
            n.next = new Node(data);
        }
    }
    public static void main(String[] args) {
        Node a = new Node(5);
        a.add(10);
        a.add(15);
        Node b = new Node(2);
        b.add(3);
        b.add(20);

        Node sorted = sortedMerge(a, b);
        while(sorted != null) {
            System.out.println(sorted.data + ",");
            sorted = sorted.next;
        }
    }

    private static Node sortedMerge(Node a, Node b) {
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } else {
            if (a.data > b.data) {
                b.next = sortedMerge(a, b.next);
                return b;
            } else {
                a.next = sortedMerge(a.next, b);
                return a;
            }
        }
    }
}
