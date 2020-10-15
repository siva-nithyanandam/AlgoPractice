package linkedlists;

/**
 * You have two numbers represented by a linked list, where each node contains a single
 * digit. The digits are stored in reverse order, such that the 1's digit is at the head
 * of the list. Write a function that adds the two numbers and returns the sum as a
 * linked list.
 * Example
 * Input: (7 -> 1 -> 6) + (5 -> 9 -> 2). That is, 617 + 295
 * Output: 2 -> 1 -> 9. That is, 912.
 *
 * Follow Up:
 * Suppose the digits are stored in forward order. Repeat the above problem.
 * Example
 * Input: (6 -> 1 -> 7) + (2 -> 9 -> 5). That is, 617 + 295
 * Output: 9 -> 1 -> 2. That is, 912.
 */

/**
 * For #2 problem, its bit tricky. Take only matching lenghts of two lists and add.
 * Once thats complete, take remaining
 * numbers from big list and add it.
 */
public class SumLists {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node list1 = new Node(5);
        list1.next = new Node(4);
        list1.next.next = new Node(3);
        list1.next.next.next = new Node(2);
        list1.next.next.next.next = new Node(1);

        Node list2 = new Node(8);
        list2.next = new Node(7);
        list2.next.next = new Node(6);

        Node result = doSumOnReverseOrder(list1, list2);
        System.out.println("doSumOnReverseOrder:");
        printList(result);

        Node result1 = doSumOnForwardOrder(list1, list2);
        if (result1.data == 0) {
            result1 = result1.next;
        }
        System.out.println("doSumOnForwardOrder:");
        printList(result1);
    }

    private static void printList(Node result) {
        Node n = result;
        while(n != null) {
            System.out.print(n.data + ",");
            n = n.next;
        }
    }

    private static Node doSumOnForwardOrder(Node l1, Node l2) {

        int m = getSize(l1);
        int n = getSize(l2);

        Node h1, h2, curr;
        int diff;

        if(m > n) {
            h1 = l1;
            h2 = l2;
            diff = m - n;
        } else {
            h1 = l2;
            h2 = l1;
            diff = n - m;
        }

        curr = h1;
        while(diff-- > 0) {
            curr = curr.next;
        }

        Node sum = sameSizeSum(curr, h2);
        sum = addMissingNodes(h1, sum, curr);
        return sum;
    }

    private static Node addMissingNodes(Node h1, Node pSum, Node curr) {
        if (h1 != curr) {
            pSum = addMissingNodes(h1.next, pSum, curr);
            int sum = h1.data + pSum.data;
            pSum.data = sum % 10;
            Node n = new Node(sum/10);
            n.next = pSum;
            pSum = n;
        }
        return pSum;
    }

    private static Node sameSizeSum(Node n1, Node n2) {
        int sum;
        Node n;

        if(n1.next == null) {
            sum = n1.data + n2.data;
            n = new Node(sum/10);
            n.next = new Node(sum%10);
        } else {
            n = sameSizeSum(n1.next, n2.next);
            sum = n1.data + n2.data + n.data;
            n.data = sum%10;
            Node temp = new Node(sum/10);
            temp.next = n;
            n = temp;
        }
        return n;
    }

    private static int getSize(Node node) {
        int count = 0;
        Node n = node;
        while(n != null) {
            n = n.next;
            count++;
        }
        return count;
    }

    private static Node doSumOnReverseOrder(Node l1, Node l2) {

        int c = 0;
        Node result = null, curr = null;
        int sum;

        while(l1 != null || l2 != null || c > 0) {
            sum = 0;
            if (l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }
            sum += c;

            if (result == null) {
                result = new Node(sum % 10);
                curr = result;
            } else {
                Node n = new Node(sum % 10);
                curr.next = n;
                curr = n;
            }
            c = sum/10;
        }
        return result;
    }
}
