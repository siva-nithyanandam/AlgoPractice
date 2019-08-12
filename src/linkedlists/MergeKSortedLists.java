package linkedlists;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */

/**
 * Divide and conquer. Take 2 each time.
 * O(nk log k), where n = number of elements in the linked list, K = number of linked lists
 */
public class MergeKSortedLists {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(4);
        a.next.next = new ListNode(5);

        ListNode b = new ListNode(1);
        b.next = new ListNode(3);
        b.next.next = new ListNode(4);

        ListNode c = new ListNode(2);
        c.next = new ListNode(6);

        ListNode[] lists = {a, b, c};
        ListNode res = mergeKLists(lists);
        while (res != null) {
            System.out.print(res.val + ",");
            res = res.next;
        }
    }

    private static ListNode sortedMerge(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;
        while(a != null && b != null) {
            if (a.val < b.val) {
                curr.next = a;
                a = a.next;
            } else {
                curr.next = b;
                b = b.next;
            }
            curr = curr.next;
        }
        curr.next = a == null ? b : a;
        return dummy.next;
    }
    
    public static ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private static ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return lists[start];
        }
        int mid = (start + end)/2;
        ListNode left = mergeKLists(lists, start, mid);
        ListNode right = mergeKLists(lists, mid + 1, end);
        return sortedMerge(left, right);
    }
}
