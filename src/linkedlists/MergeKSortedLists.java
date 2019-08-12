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
        if (a == null) {
            return b;
        } else if (b == null) {
            return a;
        } else {
            if (a.val > b.val) {
                b.next = sortedMerge(a, b.next);
                return b;
            } else {
                a.next = sortedMerge(a.next, b);
                return a;
            }
        }
    }
    
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int end = lists.length - 1;
        int start = 0;
        while (start < end) {
            while (start < end) {
                lists[start] = sortedMerge(lists[start], lists[end]);
                start++;
                end--;
            }
            start = 0;
        }
        return lists[start];
    }
}
