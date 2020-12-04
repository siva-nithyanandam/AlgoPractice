package linkedlists;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are
 * stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and
 * return the sum as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: l1 = [2,4,3], l2 = [5,6,4] Output: [7,0,8] Explanation: 342 + 465 = 807. Example 2:
 * <p>
 * Input: l1 = [0], l2 = [0] Output: [0] Example 3:
 * <p>
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9] Output: [8,9,9,9,0,0,0,1]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in each linked list is in the range [1, 100]. 0 <= Node.val <= 9 It is
 * guaranteed that the list represents a number that does not have leading zeros.
 */
public class AddTwoNumbers {

  public class ListNode {

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

  public ListNode addTwoNumbers_faster(ListNode l1, ListNode l2) {
    int carry = 0;
    ListNode temp = new ListNode(99);
    ListNode head = temp;
    int sum;

    while (l1 != null || l2 != null) {
      sum = 0;

      if (l1 != null) {
        sum += l1.val;
        l1 = l1.next;
      }
      if (l2 != null) {
        sum += l2.val;
        l2 = l2.next;
      }

      sum += carry;
      carry = 0;

      if (sum >= 10) {
        sum %= 10;
        carry = 1;
      }

      temp.next = new ListNode(sum);
      temp = temp.next;

    }

    if (carry == 1) {
      temp.next = new ListNode(1);
    }

    return head.next;
  }

  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    int carry = 0;
    ListNode dummy = new ListNode(-1);
    ListNode curr = dummy;
    int r;
    do {
      r = carry + l1.val + l2.val;
      curr.next = new ListNode(r%10);
      carry = r/10;
      curr = curr.next;
      l1 = l1.next;
      l2 = l2.next;
    } while(l1 != null && l2 != null);

    if (l1 == null) {
      while(l2 != null) {
        r = carry + l2.val;
        curr.next = new ListNode(r%10);
        carry = r/10;
        curr = curr.next;
        l2 = l2.next;
      }
    } else {
      while(l1 != null) {
        r = carry + l1.val;
        curr.next = new ListNode(r%10);
        carry = r/10;
        curr = curr.next;
        l1 = l1.next;
      }
    }
    if (carry != 0) {
      curr.next = new ListNode(carry);
    }
    return dummy.next;
  }
}
