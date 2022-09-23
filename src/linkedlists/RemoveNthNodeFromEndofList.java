package linkedlists;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 *
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Follow up: Could you do this in one pass?
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 * Example 2:
 *
 * Input: head = [1], n = 1
 * Output: []
 * Example 3:
 *
 * Input: head = [1,2], n = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is sz.
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class RemoveNthNodeFromEndofList {

   //Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static void main(String[] args) {

    }

  public ListNode removeNthFromEnd_0ms(ListNode head, int n) {
    ListNode ptr1 = null;
    ListNode ptr2 = head;
    int i = 1;
    while (i < n) {
      ptr2 = ptr2.next;
      i++;
    }

    while (ptr2.next != null) {
      if (ptr1 == null) {
        ptr1 = head;
      } else {
        ptr1 = ptr1.next;
      }
      ptr2 = ptr2.next;
    }

    if (ptr1 == null) {
      return head.next;
    } else {
      ptr1.next = ptr1.next.next;
      return head;
    }
  }

  public ListNode removeNthFromEnd(ListNode head, int n) {
    int first = 0;

    ListNode currentFirst = head;
    ListNode currentSecond = null;
    while(currentFirst.next != null) {
      currentFirst = currentFirst.next;
      first++;
      if (first == n) {
        currentSecond = head;
      } else if (first > n) {
        currentSecond = currentSecond.next;
      }
    }
    if (currentSecond == null) {
      head = head.next;
    } else if (currentSecond.next != null) {
      currentSecond.next = currentSecond.next.next;
    }
    return head;
  }

  public ListNode removeNthFromEnd_from_internet(ListNode head, int n) {
    ListNode np = new ListNode(0);
    np.next = head;
    ListNode fp = np;
    ListNode sp = fp;

    while(n > 0) {
      fp = fp.next;
      n--;
    }

    while(fp.next != null) {
      fp = fp.next;
      sp = sp.next;
    }
    sp.next = sp.next.next;
    return np.next;
  }
}
