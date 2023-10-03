package linkedlists;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 * Aug 17,2023 - 8:05 AM
 */

public class OddEvenLinkedList {

    public static void main(String[] args) {
        OddEvenLinkedList o = new OddEvenLinkedList();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        System.out.println(o.oddEvenList(head));
    }

    public ListNode oddEvenList_simple(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public ListNode oddEvenList(ListNode head) {
        ListNode odds = head;
        ListNode evens = new ListNode(-1);
        ListNode evensBkup = evens;

        while (odds != null) {
            evens.next = odds.next;
            evens = evens.next;
            if (odds.next == null || odds.next.next == null) {
                odds.next = evensBkup.next;
                return head;
            }
            odds.next = odds.next.next;
            odds = odds.next;
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {
    }
    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}