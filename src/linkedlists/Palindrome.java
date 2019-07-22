package linkedlists;

/**
 * Implement a function to check if a linked list is a palindrome.
 */

/**
 * Idea is to compare first and last, second and second last and so on.
 * Recursion technique is important here.
 */
public class Palindrome {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private static boolean isPalindrome(Node head, Node tail) {
        Node curr = head;
        if (tail == null) {
            return true;
        }
        boolean isMatched = isPalindrome(head, tail.next);
        if(!isMatched || head.next.data != tail.data) {
            return false;
        }
        head.next = head.next.next;
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(3);
        head.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next = new Node(1);
//        head.next.next.next.next.next.next.next = new Node(1);

        Node dummyHead = new Node(0);
        dummyHead.next = head;
        System.out.println(isPalindrome(dummyHead, head));
    }
}
